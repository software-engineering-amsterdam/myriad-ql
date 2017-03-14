module QL
  module AST
    class BooleanLiteral < Literal
      def to_value
        return true if value == 'true'
        return false if value == 'false'
      end

      def to_type
        BooleanType.new
      end
    end
  end
end
