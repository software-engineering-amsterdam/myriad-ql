require 'parslet'

module QL
  module Parser
    class Transformer < Parslet::Transform
      include AST
      # variable
      rule(variable: simple(:name)) { Variable.new(name) }

      # types
      rule(type: 'boolean') { BooleanType.new}
      rule(type: 'integer') { IntegerType.new }
      rule(type: 'money') { MoneyType.new }
      rule(type: 'string') { StringType.new }
      rule(type: 'decimal') { DecimalType.new }
      rule(type: 'date') { DateType.new }

      # literal
      rule(boolean_literal: simple(:value)) { BooleanLiteral.new(value) }
      rule(integer_literal: simple(:value)) { IntegerLiteral.new(value) }
      rule(string_literal: simple(:value)) { StringLiteral.new(value) }


      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type) }) { Question.new(string_literal, variable, type) }
      rule(question: { label: simple(:string_literal), id: simple(:variable), type: simple(:type), expression: subtree(:expression) }) { Question.new(string_literal, variable, type, expression) }

      # rule(question: subtree(:aap)) { p aaps }
      # question
      # TODO: BooleanType.new -> BooleanType
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'boolean' }) { Question.new(string, Variable.new(variable), BooleanType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'integer' }) { Question.new(string, Variable.new(variable), IntegerType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'money' }) { Question.new(string, Variable.new(variable), MoneyType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'string' }) { Question.new(string, Variable.new(variable), StringType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'decimal' }) { Question.new(string, Variable.new(variable), DecimalType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'date' }) { Question.new(string, Variable.new(variable), DateType) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'boolean', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), BooleanType, expression) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'integer', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), IntegerType, expression) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'money', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), MoneyType, expression) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'string', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), StringType, expression) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'decimal', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), DecimalType, expression) }
      # rule(question: { string: simple(:string), variable: simple(:variable), type: 'date', expression: subtree(:expression) }) { Question.new(string, Variable.new(variable), DateType, expression) }

      # if statement
      rule(if_statement: { expression: subtree(:expression), block: subtree(:block) }) { IfStatement.new(expression, block) }


      # form
      rule(form: { id: simple(:variable), block: subtree(:block) }) { Form.new(variable, block) }

      # negation: ! -
      rule(negation: '!', variable: simple(:variable)) { BooleanNegation.new(Variable.new(variable)) }
      rule(negation: '-', variable: simple(:variable)) { IntegerNegation.new(Variable.new(variable)) }
      rule(boolean_negation: simple(:boolean_negation), boolean: simple(:boolean)) { BooleanNegation.new(BooleanLiteral.new(boolean)) }
      rule(integer_negation: simple(:integer_negation), integer: simple(:integer)) { IntegerNegation.new(IntegerLiteral.new(integer)) }

      # boolean: && ||
      rule(left: subtree(:left), operator: '&&', right: subtree(:right)) { And.new(left, right) }
      rule(left: subtree(:left), operator: '||', right: subtree(:right)) { Or.new(left, right) }

      # arithmetic: + - / *
      rule(left: subtree(:left), operator: '+', right: subtree(:right)) { Add.new(left, right) }
      rule(left: subtree(:left), operator: '-', right: subtree(:right)) { Subtract.new(left, right) }
      rule(left: subtree(:left), operator: '/', right: subtree(:right)) { Divide.new(left, right) }
      rule(left: subtree(:left), operator: '*', right: subtree(:right)) { Multiply.new(left, right) }

      # comparison: == != < > <= >=
      rule(left: subtree(:left), operator: '==', right: subtree(:right)) { Equal.new(left, right) }
      rule(left: subtree(:left), operator: '!=', right: subtree(:right)) { NotEqual.new(left, right) }
      rule(left: subtree(:left), operator: '<', right: subtree(:right)) { Less.new(left, right) }
      rule(left: subtree(:left), operator: '>', right: subtree(:right)) { Greater.new(left, right) }
      rule(left: subtree(:left), operator: '<=', right: subtree(:right)) { LessEqual.new(left, right) }
      rule(left: subtree(:left), operator: '>=', right: subtree(:right)) { GreaterEqual.new(left, right) }
    end
  end
end