module QL
  module AST
    class Expression
      attr_reader :expression

      def initialize(expression)
        @expression = expression
      end

      def accept(visitor)
        visitor.visit_expression(self)
      end
    end
  end
end
