class BinaryExpression
  attr_reader :left, :symbol, :right

  def initialize(left, symbol, right)
    @left = left
    @symbol = symbol
    @right = right
  end
end

class BooleanExpression < BinaryExpression
end
