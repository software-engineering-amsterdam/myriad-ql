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

      def eval
        self
      end

      def eval_type
        IntegerType
      end
    end

    class BooleanLiteral < Literal
      def accept_types
        [BooleanType]
      end

      def to_value
        return true if value == 'true'
        return false if value == 'false'
      end
    end

    class IntegerLiteral < Literal
      def accept_types
        [IntegerType]
      end

      def to_value
        value.to_i
      end
    end

    class StringLiteral < Literal
      def accept_types
        [StringType]
      end

      def to_value
        value.to_s
      end
    end
  end
end