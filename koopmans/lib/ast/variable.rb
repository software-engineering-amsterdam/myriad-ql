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



class Transformer < Parslet::Transform
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end
end