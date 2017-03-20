module QL
  module AST
    # arithmetic: - + * /
    class ArithmeticExpression
      attr_accessor :operator, :expression

      def initialize(operator, expression)
        @operator = operator
        @expression = expression
      end

      def accept(left, visitor)
        visitor.visit_arithmetic_expression(left, self)
      end
    end
  end
end