require 'parslet'

class Variable
  attr_reader :name

  def initialize(name)
    @name = name.to_s
  end

  def accept(visitor)
    visitor.visit_variable(self)
  end
end

class Parser < Parslet::Parser
  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:variable_assignment) do
    variable >> str(':') >> spaces?
  end
end