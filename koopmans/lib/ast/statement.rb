class IfStatement
  attr_reader :expression, :block

  def initialize(expression, block)
    @expression = expression
    @block = block
  end
end

class Question
  attr_reader :label, :variable, :type, :assignment

  def initialize(label, variable, type, expression=nil)
    @label = label
    @variable = variable
    @type = type
    @assignment = expression if expression
  end
end