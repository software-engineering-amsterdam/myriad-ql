require_relative '../ast/statement'
require_relative '../ast/form'
require_relative '../ast/expression'
require_relative '../ast/type'
require_relative '../ast/literal'
require 'parslet'

# defines rules to match parts of a captured tree and transform them into abstract syntax tree
class Transformer < Parslet::Transform

  # form
  rule(form: {variable: simple(:variable), block: subtree(:block)}) do
    Form.new(Variable.new(variable), block)
  end

  # questions
  [BooleanType, IntegerType,DateType, DecimalType, StringType, MoneyType].each do |type|
    rule(question: {string: simple(:string), variable: simple(:variable), type: type.to_type}) do
      Question.new(string, Variable.new(variable), type.new)
    end
    rule(question: {string: simple(:string), variable: simple(:variable), type: type.to_type, expression: subtree(:expression)}) do
      Question.new(string, Variable.new(variable), type.new, expression)
    end
  end

  # if statement
  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end


  # variable
  rule(variable: simple(:variable)) do
    Variable.new(variable)
  end

  # negative variable
  [BooleanNegation, IntegerNegation].each do |singleton_expression|
    rule(negation: singleton_expression.to_operator, variable: simple(:variable)) do
      singleton_expression.new(Variable.new(variable))
    end
  end

  [Subtract, Add, Multiply, Divide, Or, And, Less, Greater, LessEqual, GreaterEqual, Equal, NotEqual].each do |binary_expression|
    rule({left: subtree(:left), operator: binary_expression.to_operator, right: subtree(:right)}) do
      binary_expression.new(left, right)
    end
  end



  # boolean literal
  rule(boolean: simple(:boolean)) do
    BooleanLiteral.new(boolean)
  end

  # negative boolean literal
  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) do
    BooleanNegation.new(BooleanLiteral.new(boolean))
  end

  # integer literal
  rule(integer: simple(:integer)) do
    IntegerLiteral.new(integer)
  end

  # negative integer literal
  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) do
    IntegerNegation.new(IntegerLiteral.new(integer))
  end

  # string literal
  rule(string: simple(:string)) do
    StringLiteral.new(string)
  end
end
