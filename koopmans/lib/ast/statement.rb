require 'parslet'

class IfStatement
  # extend Parslet
  attr_reader :expression, :block

  def initialize(expression, block)
    @expression = expression
    @block = block
  end
end

class Question
  extend Parslet

  attr_reader :label, :variable, :type, :assignment

  def initialize(label, variable, type, expression=nil)
    @label = label.to_s
    @variable = variable
    @type = type
    @assignment = expression if expression
  end
end


module StatementParser
  include Parslet

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
