class IfStatement
  attr_reader :expression, :block

  def initialize(expression, block)
    @expression = expression
    @block = block
  end
end