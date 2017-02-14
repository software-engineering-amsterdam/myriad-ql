require_relative 'question'
require_relative 'form'
require_relative 'if_statement'
require_relative 'binary_expression'
require_relative 'variable'
require_relative 'type'

# replaces the words node
class Ast < Parslet::Transform
  # create form
  rule(form: {variable: simple(:variable), block: subtree(:block)}) do
    Form.new(Variable.new(variable), block)
  end

  # create variable
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end

  # create Question
  rule(question: {label: simple(:label), variable: simple(:variable), type: simple(:type)}) do
    # Question.new(label, Variable.new(variable), Object.const_get((type.to_s.capitalize + 'Type')))
    Question.new(label, Variable.new(variable), type)
  end

  # def question_type(type)
  #   case type
  #     when 'boolean'
  #       BooleanType.new
  #
  #   end
  # end

  # rule(type: simple(:boolean)) do
  #   nil
  # end

  # create if statement
  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end






  # # create Form
  # rule(form: {variable: simple(:variable), block: subtree(:block)}) do
  #   Form.new(variable, block)
  # end
  #
  # # create Question
  # rule(question: {label: simple(:label), variable: simple(:variable), type: simple(:type)}) do
  #   Question.new(label, variable, type)
  # end
  #
  rule(question: {label: simple(:label), variable: simple(:variable), type: simple(:type), expression: subtree(:expression)}) do
    Question.new(label, variable, type, expression)
  end
  #
  # # create Question with expression
  # # TODO parse expression
  # #
  # # rule(
  # #     :left => simple(:left),
  # #     :right => simple(:right),
  # #     :operator => '+')                     { Addition.new(left, right) }
  #
  # # rule(:label => simple(:label), :variable => simple(:variable), :type => simple(:type), :expression => subtree(:expression)) do
  # #   # TODO parse expression
  # #   # Question.new(label, variable, type)
  # # end
  #
  # # rule(expression: {left: subtree(:left), arithmetic: simple(:arithmetic), right: subtree(:right)}) do
  # #   nil
  # # end
  # #
  # # rule(expression: {variable: simple(:x)}) do
  # #   nil
  # # end

  # rule({left: subtree(:left), arithmetic: simple(:arithmetic), right: subtree(:right)}) do
  #   ArithmeticExpression.new(left, arithmetic, right)
  # end
  #
  # rule({left: subtree(:left), boolean: simple(:boolean), right: subtree(:right)}) do
  #   BooleanExpression.new(left, boolean, right)
  # end
  #
  # # rule(if_statement: {expresision: subtree(:expression), block: subtree(:block)}) do
  # #   # IfStatement.new(expression, block)
  # #   nil
  # # end
  # rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
  #   IfStatement.new(expression, block)
  # end

end