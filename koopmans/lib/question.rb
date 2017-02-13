class Question
  def initialize(label, variable, type, expression=nil)
    @label = label
    @variable = variable
    @type = type
    @assignment = expression
  end
end