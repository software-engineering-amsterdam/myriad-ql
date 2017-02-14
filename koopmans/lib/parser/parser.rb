require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end
  rule(:spaces?) do
    spaces.maybe
  end

  def self.symbols(symbols)
    symbols.each do |name, symbol|
      rule(name) { str(symbol) }
    end
  end

  symbols left_brace: '{',
          right_brace: '}',
          left_parenthesis: '(',
          right_parenthesis: ')',
          quote: '"',
          colon: ':',
          assign: '=',

          subtract: '-',
          add: '+',
          multiply: '*',
          divide: '/',

          less: '<',
          less_equal: '<=',
          greater: '>',
          greater_equal: '>=',
          equal: '==',
          not_equal: '!=',

          logical_and: '&&',
          logical_or: '||',
          negate: '!'

  # expression
  rule(:calculation) do
    variable_or_literal.as(:left) >> (boolean | comparison | arithmetic) >> expression.as(:right)
  end

  rule(:arithmetic) do
    (subtract | add | multiply | divide).as(:arithmetic) >> spaces?
  end

  rule(:comparison) do
    ( less_equal | greater_equal | equal | not_equal | less | greater ).as(:comparison) >> spaces?
  end

  rule(:boolean) do
    (logical_and | logical_or | negate).as(:boolean) >> spaces?
  end

  rule(:expression) do
    left_parenthesis >> spaces? >> expression.as(:expression) >> spaces? >> right_parenthesis >> spaces? | calculation | variable_or_literal
  end


  # question
  rule(:type) do
    (str('boolean') | str('integer') | str('date') | str('decimal') | str('string') | str('money')).as(:type) >> spaces?
  end


  # variables or literals
  rule(:variable_or_literal) do
    (variable | boolean_literal | integer_literal | string_literal) >> spaces?
  end

  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:integer_literal) do
    match('[0-9]').repeat(1).as(:integer)
  end

  rule(:boolean_literal) do
    (str('true') | str('false')).as(:boolean)
  end

  rule(:string_literal) do
    quote >> match('[^"]').repeat.as(:string) >> quote >> spaces?
  end

  rule(:variable_assignment) do
    variable_or_literal >> colon >> spaces?
  end

  rule(:assignment?) do
    (assign >> spaces? >> expression).maybe >> spaces?
  end

  rule(:question) do
    (string_literal >> variable_assignment >> type >> assignment?).as(:question) >> spaces?
  end


  # if block
  rule(:block) do
    left_brace >> spaces? >> (question | if_statement).repeat.as(:block) >> right_brace >> spaces?
  end

  rule(:if_statement) do
    (str('if') >> spaces? >> expression >> block).as(:if_statement)
  end


  # form
  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable_or_literal >> spaces? >> block).as(:form)
  end

  root :form
end