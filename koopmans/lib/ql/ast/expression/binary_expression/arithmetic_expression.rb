module QL
  module AST
    class ArithmeticExpression < BinaryExpression
      def accept(left, visitor)
        visitor.visit_arithmetic_expression(left, self)
      end
    end
  end
end