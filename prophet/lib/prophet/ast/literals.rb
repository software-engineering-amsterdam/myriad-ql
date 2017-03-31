module Prophet
  module Ast
    class Literal < Node.new(:value)
    end

    class TextLiteral < Literal
      def associated_type
        TextType.new
      end

      def eval(context)
        value.to_s
      end
    end

    class NumberLiteral < Literal
      def associated_type
        NumberType.new
      end

      def eval(context)
        value.to_i
      end
    end

    class BoolLiteral < Literal
      def associated_type
        BoolType.new
      end

      def eval(context)
        value == 'true'
      end
    end
  end
end
