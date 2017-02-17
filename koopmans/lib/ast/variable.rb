require 'parslet'

class Variable
  attr_reader :name

  def initialize(name)
    @name = name.to_s
  end
end

class Parslet::Parser
  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:variable_assignment) do
    variable >> str(':') >> spaces?
  end
end

class Parslet::Transform
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end
end