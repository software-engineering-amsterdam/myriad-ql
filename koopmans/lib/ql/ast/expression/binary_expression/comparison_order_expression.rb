module QL
  module AST
    class ComparisonOrderExpression < BinaryExpression
      def accept(left, visitor)
        visitor.visit_comparison_order_expression(left, self)
      end
    end
  end
end
