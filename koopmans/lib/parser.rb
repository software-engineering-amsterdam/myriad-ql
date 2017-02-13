require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end
  rule(:spaces?) do
    spaces.maybe
  end

  # rule(:integer) do
  #   match('[0-9]').repeat(1).as(:integer) >> spaces?
  # end

  rule(:calculation) do
    variable.as(:left) >> operator.as(:operator) >> expression.as(:right)
  end

  rule(:expression) do
    left_parenthesis >> spaces? >> expression.as(:expression) >> spaces? >> right_parenthesis >> spaces? | calculation | variable
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

          subtract: '-',
          add: '+',
          multiply: '*',
          divide: '/',
          assign: '=',

          boolean_type: 'boolean',
          integer_type: 'integer',
          string_type: 'string',
          money_type: 'money',

          if_: 'if',
          form_: 'form'


  # question(s) with optional expression
  rule(:label) do
    quote >> match('[^"]').repeat.as(:label) >> quote >> spaces?
  end

  rule(:type) do
    (boolean_type | integer_type | string_type | money_type).as(:type) >> spaces?
  end

  rule(:variable) do
    match('\w+').repeat(1).as(:variable) >> spaces?
  end

  rule(:variable_assignment) do
    variable >> colon >> spaces?
  end

  rule(:operator) do
    (subtract | add | multiply | divide).as(:operator) >> spaces?
  end

  rule(:assignment?) do
    (assign >> spaces? >> expression).maybe >> spaces?
  end

  rule(:question) do
    (label >> variable_assignment >> type >> assignment?).as(:question) >> spaces?
  end


  # if block
  rule(:condition) do
    spaces? >> left_parenthesis >> spaces? >> variable.as(:condition) >> right_parenthesis >> spaces?
  end

  rule(:block) do
    left_brace >> spaces? >> (question | if_statement).repeat.as(:block) >> right_brace >> spaces?
  end

  rule(:if_statement) do
    (if_ >> condition >> block).as(:if_statement)
  end


  # form
  rule(:form) do
    (spaces? >> form_ >> spaces? >> variable >> spaces? >> block)
  end

  root :form
end
