require 'parslet'

class Form
  attr_reader :variable
  attr_reader :statements

  def initialize(variable, statements)
    @variable = variable
    @statements = statements
  end

  # go through each element and 'visit' it
  def accept(visitor)
    @statements.each do |element|
      element.accept(visitor)
    end
  end
end

class Parslet::Parser
  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end
end

class Parslet::Transform
  rule(form: {variable: simple(:variable), block: subtree(:block)}) do
    Form.new(Variable.new(variable), block)
  end
end