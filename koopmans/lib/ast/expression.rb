class Variable
  attr_reader :name

  def initialize(name)
    @name = name.to_s
  end
end

class SingletonExpression
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end
end

class BinaryExpression
  attr_reader :left, :right

  def initialize(left, right)
    @left = left
    @right = right
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