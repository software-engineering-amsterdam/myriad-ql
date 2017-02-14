class BinaryExpression
  attr_reader :left, :right

  def initialize(left, right)
    @left = left
    @right = right
  end
end


# booleans && || !
class And < BinaryExpression
end

class Or < BinaryExpression
end

# class Negate < BinaryExpression
# end


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