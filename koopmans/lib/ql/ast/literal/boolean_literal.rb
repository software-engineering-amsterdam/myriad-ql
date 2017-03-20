module QL
  module AST
    class BooleanLiteral < Literal
      def initialize(value)
        @value = to_boolean(value.to_s)
      end

      def to_boolean(value)
        return true if value == 'true'
        return false if value == 'false'
      end

      def to_type
        BooleanType.new
      end
    end
  end
end
