require_relative 'question'
require_relative 'form'
require_relative 'if_statement'

# replaces the words node
class Ast < Parslet::Transform
  # create Form
  rule(:variable => simple(:variable), :block => subtree(:block)) do
    Form.new(variable, block)
  end

  # remove :question
  rule(:question => subtree(:question)) do
    question
  end

  # create Question
  rule(:label => simple(:label), :variable => simple(:variable), :type => simple(:type)) do
    Question.new(label, variable, type)
  end

  # create Question with expression
  # TODO parse expression
  #
  # rule(
  #     :left => simple(:left),
  #     :right => simple(:right),
  #     :operator => '+')                     { Addition.new(left, right) }

  # rule(:label => simple(:label), :variable => simple(:variable), :type => simple(:type), :expression => subtree(:expression)) do
  #   # TODO parse expression
  #   # Question.new(label, variable, type)
  # end

  rule(:if_statement => {:condition => {:variable => simple(:x)}, :block => subtree(:block)}) do
    IfStatement.new(x, block)
  end
end