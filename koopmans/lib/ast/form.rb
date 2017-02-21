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
  end
end

class Parser < Parslet::Parser
  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end
end
