require 'parslet'

module QL
  module Parser
    class ExpressionTransformer < Parslet::Transform
      include AST

      # negation
      rule([negation_operator: '-', single: simple(:single)]) { IntegerNegation.new('-', single) }
      rule([negation_operator: '!', single: simple(:single)]) { BooleanNegation.new('!', single) }

      # binary expression
      rule(arithmetic_operator: simple(:operator), right: simple(:right))       { ArithmeticExpression.new(operator, right) }
      rule(comparison_equal_operator: simple(:operator), right: simple(:right)) { ComparisonEqualExpression.new(operator, right) }
      rule(comparison_order_operator: simple(:operator), right: simple(:right)) { ComparisonOrderExpression.new(operator, right) }
      rule(boolean_operator: simple(:operator), right: simple(:right))          { BooleanExpression.new(operator, right) }

      # literal
      rule(left: simple(:literal)) { literal }

      # expression
      rule(sequence(:expressions)) { ExpressionSequence.new(expressions) }
    end
  end
end
