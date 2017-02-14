require_relative '../ast/type'
require_relative '../ast/expression'
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
          assign: '='


  # expression
  rule(:calculation) do
    variable_or_literal.as(:left) >> operator >> expression.as(:right)
  end

  # rule(:operator) do
  #   (str('-') | str('+') | str('*') | str('/') | str('<=') | str('>=') | str('==') | str('!=') | str('<') | str('>') | str('&&') | str('||') | str('!')).as(:operator) >> spaces?
  # end

  rule(:operator) do
    BinaryExpression.descendants.map { |type| str(type.to_operator) }.reduce(&:|).as(:operator) >> spaces?
  end

  rule(:expression) do
    left_parenthesis >> spaces? >> expression.as(:expression) >> spaces? >> right_parenthesis >> spaces? | calculation | variable_or_literal
  end


  # question
  # rule(:type) do
  #   (str('boolean') | str('integer') | str('date') | str('decimal') | str('string') | str('money')).as(:type) >> spaces?
  # end

  rule(:type) do
    Type.descendants.map { |type| str(type.to_type) }.reduce(&:|).as(:type) >> spaces?
  end


  # variables or literals
  rule(:variable_or_literal) do
    (boolean_negation? >> boolean_literal | integer_negation? >> integer_literal | string_literal | negation? >> variable) >> spaces?
  end

  rule(:integer_negation?) do
    str('-').as(:integer_negation).maybe
  end

  rule(:boolean_negation?) do
    str('!').as(:boolean_negation).maybe
  end

  rule(:negation?) do
    (str('!') | str('-')).as(:negation).maybe
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
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end

  root :form
end