require_relative 'question'
require_relative 'form'

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

  rule(:if_statement => {:condition => {:variable => simple(:x)}, :block => [subtree(:block)]}) do
    x
  end
end