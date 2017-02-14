class Form
  attr_reader :variable
  attr_reader :block

  def initialize(variable, block)
    @variable = variable
    @block = block
  end
end