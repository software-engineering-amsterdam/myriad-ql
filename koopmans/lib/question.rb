class Question
  def initialize(label, variable, type, enabling_condition = true)
    @label = label
    @variable = variable
    @type = type
    @enabling_condition = enabling_condition
  end
end