module QL
  module AST
    class IntegerLiteral
      attr_reader :value

      def initialize(value)
        @value = value.to_i
      end

      def accept(visitor)
        visitor.visit_integer_literal(self)
      end
    end
  end
end
