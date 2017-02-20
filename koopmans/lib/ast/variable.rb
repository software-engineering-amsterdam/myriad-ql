require 'parslet'

class Variable
  attr_reader :name
  attr_accessor :tk_variable

  def initialize(name)
    @name = name.to_s
  end

  def eval
    tk_variable.value
  end

  # def accept(type_checker)
  #   type_checker.visit_variable(self)
  # end
end

class Parser < Parslet::Parser
  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:variable_assignment) do
    variable >> str(':') >> spaces?
  end
end

class Transformer < Parslet::Transform
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end
end