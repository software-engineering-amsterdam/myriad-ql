module QL
  module AST
    class BooleanLiteral
      attr_reader :value

      def initialize(value)
        @value = to_boolean(value.to_s)
      end

      def accept(visitor)
        visitor.visit_boolean_literal(self)
      end

      def to_boolean(value)
        return true if value == 'true'
        return false if value == 'false'
      end
    end
  end
end
