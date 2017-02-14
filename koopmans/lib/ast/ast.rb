require_relative 'statement'
require_relative 'form'
require_relative 'expression'
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

  # create variable with negation
  negation_types = {'!' => BooleanNegation,
                    '-' => IntegerNegation}
  rule(negation: simple(:negation), variable: simple(:variable)) do
    negation_types[negation.to_s].new(Variable.new(variable))
  end

  # Questions
  types = {'boolean' => BooleanType,
           'integer' => IntegerType,
           'date' => DateType,
           'decimal' => DecimalType,
           'string' => StringType,
           'money' => MoneyType}

  # create Question
  rule(question: {string: simple(:string), variable: simple(:variable), type: simple(:type)}) do
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


  # boolean literal
  rule(boolean: simple(:boolean)) do
    BooleanLiteral.new(boolean)
  end

  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) do
    BooleanNegation.new(BooleanLiteral.new(boolean))
  end

  # integer literal
  rule(integer: simple(:integer)) do
    IntegerLiteral.new(integer)
  end

  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) do
    IntegerNegation.new(IntegerLiteral.new(integer))
  end

  # string literal
  rule(string: simple(:string)) do
    StringLiteral.new(string)
  end

  # arithmetic expressions
  arithmetics = {'-' => Subtract,
                 '+' => Add,
                 '*' => Multiply,
                 '/' => Divide}

  rule({left: subtree(:left), arithmetic: simple(:arithmetic), right: subtree(:right)}) do
    arithmetics[arithmetic.to_s].new(left, right)
  end

  # boolean expressions
  booleans = {'||' => Or,
              '&&' => And}

  rule({left: subtree(:left), boolean: simple(:boolean), right: subtree(:right)}) do
    booleans[boolean.to_s].new(left, right)
  end

  comparisons = {'<' => Less,
                 '>' => Greater,
                 '<=' => LessEqual,
                 '>=' => GreaterEqual,
                 '==' => Equal,
                 '!=' => NotEqual}
  # comparison expressions
  rule({left: subtree(:left), comparison: simple(:comparison), right: subtree(:right)}) do
    comparisons[comparison.to_s].new(left, right)
  end
end