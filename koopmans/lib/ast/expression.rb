require 'parslet'
require_relative '../helper'

class Expression
  extend Helper
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
  attr_reader :left, :right

  def initialize(left, right)
    @left = left
    @right = right
  end
end

# booleans: && ||
class And < BinaryExpression
  def self.to_operator
    '&&'
  end

  def self.real_type
    BooleanType
  end

  def eval
    left.eval && right.eval
  end
end

class Or < BinaryExpression
  def self.to_operator
    '||'
  end

  def self.real_type
    BooleanType
  end
end


# arithmetic: - + * /
class Subtract < BinaryExpression
  def self.to_operator
    '-'
  end

  def self.real_type
    IntegerType
  end
end

class Add < BinaryExpression
  def self.to_operator
    '+'
  end

  def self.real_type
    IntegerType
  end

  def eval
    left.eval.to_i + right.eval.to_i
  end
end

class Multiply < BinaryExpression
  def self.to_operator
    '*'
  end
end

class Divide < BinaryExpression
  def self.to_operator
    '/'
  end
end

# comparisons: < > <= >= == !=
class Less < BinaryExpression
  def self.to_operator
    '<'
  end
end

class Greater < BinaryExpression
  def self.to_operator
    '>'
  end
end

class LessEqual < BinaryExpression
  def self.to_operator
    '<='
  end
end

class GreaterEqual < BinaryExpression
  def self.to_operator
    '>='
  end
end

class Equal< BinaryExpression
  def self.to_operator
    '=='
  end
end

class NotEqual < BinaryExpression
  def self.to_operator
    '!='
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

  rule(:operator) do
    BinaryExpression.descendants.map { |binary_expression| str(binary_expression.to_operator) }.reduce(&:|).as(:operator) >> spaces?
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

  BinaryExpression.descendants.each do |binary_expression|
    rule({left: subtree(:left), operator: binary_expression.to_operator, right: subtree(:right)}) do
      binary_expression.new(left, right)
    end
  end
end

