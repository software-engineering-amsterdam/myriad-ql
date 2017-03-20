module QL
  module AST
    class StringLiteral
      attr_reader :value

      def initialize(value)
        @value = value.to_s
      end

      def accept(visitor)
        visitor.visit_string_literal(self)
      end
    end
  end
end
