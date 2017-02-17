class Expression
  def self.descendants
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end
end

class SingletonExpression < Expression
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end
end

class BinaryExpression < Expression
  attr_reader :left, :right

  def initialize(left, right)
    @left = left
    @right = right
  end
end

module ExpressionRules
  include Parslet

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


# negations: ! -
class BooleanNegation < SingletonExpression
  def self.to_operator
    '!'
  end
end

class IntegerNegation < SingletonExpression
  def self.to_operator
    '-'
  end
end


# booleans: && ||
class And < BinaryExpression
  def self.to_operator
    '&&'
  end
end

class Or < BinaryExpression
  def self.to_operator
    '||'
  end
end


# arithmetic: - + * /
class Subtract < BinaryExpression
  def self.to_operator
    '-'
  end
end

class Add < BinaryExpression
  def self.to_operator
    '+'
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

