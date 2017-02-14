require_relative 'question'
require_relative 'form'
require_relative 'if_statement'
require_relative 'binary_expression'
require_relative 'variable'
require_relative 'type'
require_relative 'literal'

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

  types = {'boolean' => BooleanType,
           'integer' => IntegerType,
           'date' => DateType,
           'decimal' => DecimalType,
           'string' => StringType,
           'money' => MoneyType}

  # create Question
  rule(question: {string: simple(:string), variable: simple(:variable), type: simple(:type)}) do
    # Question.new(string, Variable.new(variable), Object.const_get((type.to_s.capitalize + 'Type')))
    Question.new(string, Variable.new(variable), types[type.to_s].new)
  end

  # create Question with assignment
  rule(question: {string: simple(:string), variable: simple(:variable), type: simple(:type), expression: subtree(:expression)}) do
    Question.new(string, variable, types[type.to_s].new, expression)
  end

  # create if statement
  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
    IfStatement.new(expression, block)
  end


  # literals
  rule(boolean: simple(:boolean)) do
    BooleanLiteral.new(boolean)
  end

  rule(integer: simple(:integer)) do
    IntegerLiteral.new(integer)
  end

  rule(string: simple(:string)) do
    StringLiteral.new(string)
  end


  # # create Form
  # rule(form: {variable: simple(:variable), block: subtree(:block)}) do
  #   Form.new(variable, block)
  # end
  #
  # # create Question
  # rule(question: {string: simple(:string), variable: simple(:variable), type: simple(:type)}) do
  #   Question.new(string, variable, type)
  # end
  #

  #
  # # create Question with expression
  # # TODO parse expression
  # #
  # # rule(
  # #     :left => simple(:left),
  # #     :right => simple(:right),
  # #     :operator => '+')                     { Addition.new(left, right) }
  #
  # # rule(:string => simple(:string), :variable => simple(:variable), :type => simple(:type), :expression => subtree(:expression)) do
  # #   # TODO parse expression
  # #   # Question.new(string, variable, type)
  # # end
  #
  # # rule(expression: {left: subtree(:left), arithmetic: simple(:arithmetic), right: subtree(:right)}) do
  # #   nil
  # # end
  # #
  # # rule(expression: {variable: simple(:x)}) do
  # #   nil
  # # end


  # arithmetic expressions
  rule({left: subtree(:left), arithmetic: simple(:arithmetic), right: subtree(:right)}) do
    arithmetics = {'-' => Subtract,
                   '+' => Add,
                   '*' => Multiply,
                   '/' => Divide}
    arithmetics[arithmetic.to_s].new(left, right)
  end

  # boolean expressions
  rule({left: subtree(:left), boolean: simple(:boolean), right: subtree(:right)}) do
    booleans = {'||' => Or,
                '&&' => And}
    booleans[boolean.to_s].new(left, right)
  end

  # comparison expressions
  rule({left: subtree(:left), comparison: simple(:comparison), right: subtree(:right)}) do
    comparisons = {'<' => Less,
                   '>' => Greater,
                   '<=' => LessEqual,
                   '>=' => GreaterEqual,
                   '==' => Equal,
                   '!=' => NotEqual}
    comparisons[comparison.to_s].new(left, right)
  end

  #
  # # rule(if_statement: {expresision: subtree(:expression), block: subtree(:block)}) do
  # #   # IfStatement.new(expression, block)
  # #   nil
  # # end
  # rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) do
  #   IfStatement.new(expression, block)
  # end

end