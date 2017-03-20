module QL
  module AST
    class BooleanExpression < BinaryExpression
      def accept(left, visitor)
        visitor.visit_boolean_expression(left, self)
      end
    end
  end
end
