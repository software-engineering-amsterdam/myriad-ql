require 'parslet'

class IfStatement
  # extend Parslet
  attr_reader :expression, :block

  def initialize(expression, block)
    @expression = expression
    @block = block
  end

  # def accept(visitor)
  #   visitor.visit_if_statement(self)
  # end
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

  # def accept(visitor)
  #   visitor.visit_question(self)
  #   # @variable.accept(type_checker)
  # end
end

class Parser < Parslet::Parser
  # include Parslet

  rule(:assignment?) do
    (str('=') >> spaces? >> expression).maybe >> spaces?
  end

  rule(:question) do
    (string_literal >> variable_assignment >> type >> assignment?).as(:question) >> spaces?
  end

  rule(:block) do
    str('{') >> spaces? >> (question | if_statement).repeat.as(:block) >> str('}') >> spaces?
  end

  rule(:if_statement) do
    (str('if') >> spaces? >> expression >> block).as(:if_statement)
  end
end

class Transformer < Parslet::Transform
  Type.descendants.each do |type|
    rule(question: {string: simple(:string), variable: simple(:variable), type: type.type}) do
      Question.new(string, Variable.new(variable), type.new)
    end
    rule(question: {string: simple(:string), variable: simple(:variable), type: type.type, expression: subtree(:expression)}) do
      Question.new(string, Variable.new(variable), type.new, expression)
    end
  end

  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end
end
