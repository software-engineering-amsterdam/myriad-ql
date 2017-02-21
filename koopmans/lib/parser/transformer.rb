require 'parslet'
require 'require_all'

require_rel '../ast'

class Transformer < Parslet::Transform
  # variable
  rule(variable: simple(:variable)) { Variable.new(variable) }

  # question
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'boolean'}) { Question.new(string, Variable.new(variable), BooleanType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'integer'}) { Question.new(string, Variable.new(variable), IntegerType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'money'}) { Question.new(string, Variable.new(variable), MoneyType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'string'}) { Question.new(string, Variable.new(variable), StringType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'decimal'}) { Question.new(string, Variable.new(variable), DecimalType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'date'}) { Question.new(string, Variable.new(variable), DateType.new) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'boolean', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), BooleanType.new, expression) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'integer', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), IntegerType.new, expression) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'money', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), MoneyType.new, expression) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'string', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), StringType.new, expression) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'decimal', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), DecimalType.new, expression) }
  rule(question: {string: simple(:string), variable: simple(:variable), type: 'date', expression: subtree(:expression)}) { Question.new(string, Variable.new(variable), DateType.new, expression) }

  # if statement
  rule(if_statement: {expression: subtree(:expression), block: subtree(:block)}) { IfStatement.new(expression, block) }

  # literal
  rule(boolean: simple(:value)) { BooleanLiteral.new(value) }
  rule(integer: simple(:value)) { IntegerLiteral.new(value) }
  rule(string: simple(:value)) { StringLiteral.new(value) }

  # form
  rule(form: {variable: simple(:variable), block: subtree(:block)}) { Form.new(Variable.new(variable), block) }

  # negation: ! -
  rule(negation: '!', variable: simple(:variable)) { BooleanNegation.new(Variable.new(variable)) }
  rule(negation: '-', variable: simple(:variable)) { IntegerNegation.new(Variable.new(variable)) }
  rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) { BooleanNegation.new(BooleanLiteral.new(boolean)) }
  rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) { IntegerNegation.new(IntegerLiteral.new(integer)) }

  # boolean: && ||
  rule({left: subtree(:left), operator: '&&', right: subtree(:right)}) { And.new(left, right) }
  rule({left: subtree(:left), operator: '||', right: subtree(:right)}) { Or.new(left, right) }

  # arithmetic: + - / *
  rule({left: subtree(:left), operator: '+', right: subtree(:right)}) { Add.new(left, right) }
  rule({left: subtree(:left), operator: '-', right: subtree(:right)}) { Subtract.new(left, right) }
  rule({left: subtree(:left), operator: '/', right: subtree(:right)}) { Divide.new(left, right) }
  rule({left: subtree(:left), operator: '*', right: subtree(:right)}) { Multiply.new(left, right) }

  # comparison: == != < > <= >=
  rule({left: subtree(:left), operator: '==', right: subtree(:right)}) { Equal.new(left, right) }
  rule({left: subtree(:left), operator: '!=', right: subtree(:right)}) { NotEqual.new(left, right) }
  rule({left: subtree(:left), operator: '<', right: subtree(:right)}) { Less.new(left, right) }
  rule({left: subtree(:left), operator: '>', right: subtree(:right)}) { Greater.new(left, right) }
  rule({left: subtree(:left), operator: '<=', right: subtree(:right)}) { LessEqual.new(left, right) }
  rule({left: subtree(:left), operator: '>=', right: subtree(:right)}) { GreaterEqual.new(left, right) }
end