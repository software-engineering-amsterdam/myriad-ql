module QL
  module AST
    class BinaryExpression < Expression
      def accept(left, visitor)
        visitor.visit_binary_expression(left, self)
      end
    end
  end
end
