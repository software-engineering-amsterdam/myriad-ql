module QL
  module AST
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