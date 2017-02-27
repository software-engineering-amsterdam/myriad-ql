require 'parslet'

class IfStatement
  # extend Parslet
  attr_reader :expression, :block

  def initialize(expression, block)
    @expression = expression
    @block = block
  end

  def accept(visitor)
    visitor.visit_if_statement(self)
  end
end

class Question
  extend Parslet

  attr_reader :label, :variable, :type, :assignment
  attr_accessor :condition

  def initialize(label, variable, type, expression=nil, condition=nil)
    @label = label.to_s
    @variable = variable
    @type = type
    @assignment = expression if expression
    @condition = condition if condition
  end

  def accept(visitor)
    visitor.visit_question(self)
  end
end




