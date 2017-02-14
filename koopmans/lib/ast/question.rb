class Question
  attr_reader :label, :variable, :type, :assignment

  def initialize(label, variable, type, expression=nil)
    @label = label
    @variable = variable
    @type = type
    @assignment = expression if expression
  end
end