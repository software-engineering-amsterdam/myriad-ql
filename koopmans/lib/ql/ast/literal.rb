module QL
  module AST
    class Literal
      attr_reader :value

      def initialize(value)
        @value = value
      end

      def accept(visitor)
        visitor.visit_literal(self)
      end
    end

    class BooleanLiteral < Literal
      def accept_types
        [BooleanType]
      end

      def eval
        return true if value == 'true'
        return false if value == 'false'
      end
    end

    class IntegerLiteral < Literal
      def accept_types
        [IntegerType]
      end

      def eval; self end
      def op(operation, other)
        left = value
        right = other.value

        IntegerLiteral.new(
          case operation
            when '+'
              left + right
            when '-'
              left - right
            when '*'
              left * right
            when '/'
              left / right
            when '=='
              left == right
            when '>'
              left > right
            when '!'
              !left
          end)
      end
    end

    class StringLiteral < Literal
      def accept_types
        [StringType]
      end

      def eval
        value.to_s
      end
    end
  end
end