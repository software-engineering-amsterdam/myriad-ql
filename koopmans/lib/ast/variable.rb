require 'parslet'

class Variable
  attr_reader :name

  def initialize(name)
    @name = name.to_s
  end

  def accept(visitor)
    visitor.visit(self)
  end

  # def rule
  #   match('\w+').repeat(1).as(:variable)
  # end
end

module VariableParser
  include Parslet

  rule(:variable) do
    match('\w+').repeat(1).as(:variable)
  end

  rule(:variable_assignment) do
    variable >> str(':') >> spaces?
  end
end