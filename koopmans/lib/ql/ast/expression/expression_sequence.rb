module QL
  module AST
    class ExpressionSequence
      attr_reader :expressions

      def initialize(expressions)
        @expressions = expressions
      end

      def accept(visitor)
        visitor.visit_expression_sequence(self)
      end
    end
  end
end
