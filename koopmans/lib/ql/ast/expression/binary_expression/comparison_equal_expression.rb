module QL
  module AST
    class ComparisonEqualExpression < BinaryExpression
      def accept(left, visitor)
        visitor.visit_comparison_equal_expression(left, self)
      end
    end
  end
end
