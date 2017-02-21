require 'parslet'
require_relative '../helper'

class Expression
  extend Helper

  def self.includes_type?(type)
    !([type].flatten & real_type).empty?
  end
end

class Negation < Expression
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end
end

class BooleanNegation < Negation
  def self.to_operator
    '!'
  end
end

class IntegerNegation < Negation
  def self.to_operator
    '-'
  end
end

class BinaryExpression < Expression
  attr_accessor :left, :right

  def initialize(left, right)
    @left = left
    @right = right
  end
end

# booleans: && ||
class BooleanExpression < BinaryExpression
  def self.real_type
    [BooleanType]
  end
end

class And < BooleanExpression
  def self.to_operator
    '&&'
  end

  def eval
    left.eval && right.eval
  end
end

class Or < BooleanExpression
  def self.to_operator
    '||'
  end
end

# arithmetic: - + * /
class ArithmeticExpression < BinaryExpression
  def self.real_type
    [IntegerType]
  end
end

class Subtract < ArithmeticExpression
  def self.to_operator
    '-'
  end
end

class Add < ArithmeticExpression
  def self.to_operator
    '+'
  end

  def eval
    left.eval.to_i + right.eval.to_i
  end
end

class Multiply < ArithmeticExpression
  def self.to_operator
    '*'
  end
end

class Divide < ArithmeticExpression
  def self.to_operator
    '/'
  end
end

# comparisons == !=
class ComparisonEqual < BinaryExpression
  def self.real_type
    [BooleanType, IntegerType, StringType]
  end
end

class Equal< ComparisonEqual
  def self.to_operator
    '=='
  end
end

class NotEqual < ComparisonEqual
  def self.to_operator
    '!='
  end
end

# comparisons: < > <= >=
class ComparisonOrdering < BinaryExpression
  def self.real_type
    [IntegerType]
  end
end

class Less < ComparisonOrdering
  def self.to_operator
    '<'
  end
end

class Greater < ComparisonOrdering
  def self.to_operator
    '>'
  end
end

class LessEqual < ComparisonOrdering
  def self.to_operator
    '<='
  end
end

class GreaterEqual < ComparisonOrdering
  def self.to_operator
    '>='
  end
end



class Parser < Parslet::Parser
  rule(:integer_negation?) do
    str('-').as(:integer_negation).maybe
  end

  rule(:boolean_negation?) do
    str('!').as(:boolean_negation).maybe
  end

  rule(:negation?) do
    (str('!') | str('-')).as(:negation).maybe
  end

  rule(:variable_or_literal) do
    (boolean_negation? >> boolean_literal | integer_negation? >> integer_literal | string_literal | negation? >> variable) >> spaces?
  end

  rule(:calculation) do
    variable_or_literal.as(:left) >> operator >> expression.as(:right)
  end

  # TODO fix this
  rule(:operator) do
    descendants = BooleanExpression.descendants +  ArithmeticExpression.descendants + ComparisonEqual.descendants + ComparisonOrdering.descendants
    descendants.map { |binary_expression| str(binary_expression.to_operator) }.reduce(&:|).as(:operator) >> spaces?
  end


  rule(:expression) do
    str('(') >> spaces? >> expression.as(:expression) >> spaces? >> str(')') >> spaces? | calculation | variable_or_literal
  end
end

class Transformer < Parslet::Transform
  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) do
    BooleanNegation.new(BooleanLiteral.new(boolean))
  end

  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) do
    IntegerNegation.new(IntegerLiteral.new(integer))
  end

  Negation.descendants.each do |singleton_expression|
    rule(negation: singleton_expression.to_operator, variable: simple(:variable)) do
      singleton_expression.new(Variable.new(variable))
    end
  end

  # TODO fix this
  descendants = BooleanExpression.descendants +  ArithmeticExpression.descendants + ComparisonEqual.descendants + ComparisonOrdering.descendants
  descendants.each do |binary_expression|
    rule({left: subtree(:left), operator: binary_expression.to_operator, right: subtree(:right)}) do
      binary_expression.new(left, right)
    end
  end

end

