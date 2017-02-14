class Variable
  attr_reader :name

  def initialize(name)
    @name = name
  end
end

class SingletonExpression
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end
end

# singletons: ! -
class BooleanNegation < SingletonExpression
end

class IntegerNegation < SingletonExpression
end

class BinaryExpression
  attr_reader :left, :right

  def initialize(left, right)
    @left = left
    @right = right
  end
end

# booleans && ||
class And < BinaryExpression
end

class Or < BinaryExpression
end

# arithmetic: - + * /
class Subtract < BinaryExpression
end

class Add < BinaryExpression
end

class Multiply < BinaryExpression
end

class Divide < BinaryExpression
end


# comparisons: < > <= >= == !=
class Less < BinaryExpression
end

class Greater < BinaryExpression
end

class LessEqual < BinaryExpression
end

class GreaterEqual < BinaryExpression
end

class Equal< BinaryExpression
end

class NotEqual < BinaryExpression
end