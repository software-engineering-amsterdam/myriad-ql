require 'parslet'

module QL
  module Parser
    class ExpressionTransformer < Parslet::Transform
      include AST

      # negation: ! -
      rule([operator: '-', single: simple(:single)]) { IntegerNegation.new('-', single) }
      rule([operator: '!', single: simple(:single)]) { BooleanNegation.new('!', single) }

      # arithmetic: + - / *
      rule(arithmetic_operator: simple(:operator), right: simple(:right)) { ArithmeticExpression.new(operator, right) }
      # rule(operator: '/', right: simple(:right)) { Divide.new(right) }
      # rule(operator: '+', right: simple(:right)) { Add.new(right) }
      # rule(operator: '-', right: simple(:right)) { Subtract.new(right) }

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
      rule(left: simple(:integer_literal)) { integer_literal }

      rule(sequence(:expression)) { Expression.new(expression) }
    end
  end
end