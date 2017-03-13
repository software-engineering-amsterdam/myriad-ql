module QL
  module AST
    class Literal
      attr_reader :value

      def initialize(value)
        @value = value.to_s
      end

      def accept(visitor)
        visitor.visit_literal(self)
      end
    end

    class BooleanLiteral < Literal
      def to_value
        return true if value == 'true'
        return false if value == 'false'
      end

      def to_type
        BooleanType.new
      end
    end

    class IntegerLiteral < Literal
      def to_value
        value.to_i
      end

      def to_type
        IntegerType.new
      end
    end

    class StringLiteral < Literal
      def to_value
        value.to_s
      end

      def to_type
        StringType.new
      end
    end
  end
end