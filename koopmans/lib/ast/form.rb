class Form
  attr_reader :variable
  attr_reader :statements

  def initialize(variable, statements)
    @variable = variable
    @statements = statements
  end
end