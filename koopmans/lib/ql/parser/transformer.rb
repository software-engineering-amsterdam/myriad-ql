require 'parslet'

module QL
  module Parser
    Int    = Struct.new(:int) {
      def eval; self end
      def op(operation, other)
        left = int
        right = other.int

        Int.new(
          case operation
            when '+'
              left + right
            when '-'
              left - right
            when '*'
              left * right
            when '/'
              left / right
            when '=='
              left == right
            when '>'
              left > right
            when '!'
              !left
          end)
      end
      def to_i
        int
      end
    }

    Seq    = Struct.new(:sequence) {
      def eval
        sequence.reduce { |accum, operation|
          p accum
          p operation
          p 'jeofjeojeo'
          operation.call(accum) }
      end
    }
    LeftOp = Struct.new(:operation, :right) {
      def call(left)
        left = left.eval

        right = self.right.eval
        pp operation
        left.op(operation, right)
      end
    }

    class Transformer < Parslet::Transform
      include AST
      # rule(integer_literal: simple(:integer_literal)) { Int.new(Integer(integer_literal)) }
      # rule(operator: simple(:operator), right: simple(:integer_literal)) { LeftOp.new(operator, integer_literal) }

      # # arithmetic: + - / *
      rule(operator: '*', right: simple(:right)) { Multiply.new(right) }
      rule(operator: '/', right: simple(:right)) { Divide.new(right) }
      rule(operator: '+', right: simple(:right)) { Add.new(right) }
      rule(operator: '-', right: simple(:right)) { Subtract.new(right) }

      # comparison: == != < > <= >=
      rule(operator: '==', right: simple(:right)) { Equal.new(right) }
      rule(operator: '!=', right: simple(:right)) { NotEqual.new(right) }
      rule(operator: '<', right: simple(:right)) { Less.new(right) }
      rule(operator: '>', right: simple(:right)) { Greater.new(right) }
      rule(operator: '<=', right: simple(:right)) { LessEqual.new(right) }
      rule(operator: '>=', right: simple(:right)) { GreaterEqual.new(right) }

      # boolean: && ||
      rule(operator: '&&', right: simple(:right)) { And.new(right) }
      rule(operator: '||', right: simple(:right)) { Or.new(right) }
      #
      # rule(left: subtree(:left), operator: '+', right: subtree(:right)) { Add.new(left, right) }



      rule(left: simple(:integer_literal)) { integer_literal }
      rule(sequence(:sequence)) { Seq.new(sequence) }

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

      # if statement
      rule(if_statement: { expression: subtree(:expression), body: subtree(:body) }) { IfStatement.new(expression, body) }

      # form
      rule(form: { id: simple(:variable), body: subtree(:body) }) { Form.new(variable, body) }

      # negation: ! -
      rule(negation: '!', variable: simple(:variable)) { BooleanNegation.new(variable) }
      rule(negation: '-', variable: simple(:variable)) { IntegerNegation.new(variable) }
      rule(negation: '!', boolean_literal: simple(:boolean_literal)) { BooleanNegation.new(boolean_literal) }
      rule(negation: '-', integer_literal: simple(:integer_literal)) { IntegerNegation.new(integer_literal) }

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