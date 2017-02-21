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
  # Type.descendants.each do |type|
  #   rule(question: {string: simple(:string), variable: simple(:variable), type: type.type}) do
  #     Question.new(string, Variable.new(variable), type.new)
  #   end
  #   rule(question: {string: simple(:string), variable: simple(:variable), type: type.type, expression: subtree(:expression)}) do
  #     Question.new(string, Variable.new(variable), type.new, expression)
  #   end
  # end

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'boolean'}) {
    Question.new(string, Variable.new(variable), BooleanType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'integer'}) {
    Question.new(string, Variable.new(variable), IntegerType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'money'}) {
    Question.new(string, Variable.new(variable), MoneyType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'string'}) {
    Question.new(string, Variable.new(variable), StringType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'decimal'}) {
    Question.new(string, Variable.new(variable), DecimalType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'date'}) {
    Question.new(string, Variable.new(variable), DateType.new)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'boolean', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), BooleanType.new, expression)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'integer', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), IntegerType.new, expression)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'money', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), MoneyType.new, expression)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'string', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), StringType.new, expression)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'decimal', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), DecimalType.new, expression)
  }

  rule(question: {string: simple(:string), variable: simple(:variable), type: 'date', expression: subtree(:expression)}) {
    Question.new(string, Variable.new(variable), DateType.new, expression)
  }

  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end
end
