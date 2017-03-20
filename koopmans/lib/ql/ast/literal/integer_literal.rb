module QL
  module AST
    class IntegerLiteral < Literal
      def initialize(value)
        @value = value.to_i
      end

      def to_value
        value.to_i
      end

      def to_type
        IntegerType.new
      end
    end
  end
end
