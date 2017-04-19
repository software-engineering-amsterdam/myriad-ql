module Prophet
  module Ast
    class Literal < Node.new(:value)
    end

    class TextLiteral < Literal
      def self.associated_type
        TextType.new
      end
    end

    class NumberLiteral < Literal
      def self.associated_type
        NumberType.new
      end
    end

    class BoolLiteral < Literal
      def self.associated_type
        BoolType.new
      end
    end
  end
end
