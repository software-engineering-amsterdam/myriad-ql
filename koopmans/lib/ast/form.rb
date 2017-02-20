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
    visitor.visit_form(self)
    # @statements.each do |element|
    #   element.accept(type_checker)
    # end
  end
end

class Parser < Parslet::Parser
  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end
end

class Transformer < Parslet::Transform
  rule(form: {variable: simple(:variable), block: subtree(:block)}) do
    Form.new(Variable.new(variable), block)
  end
end