module Prophet
  module Ast
    class Literal < Node.new(:value)
    end

    class TextLiteral < Literal
      def associated_type
        Type.new('text')
      end

      def eval(context)
        value.to_s
      end
    end

    class NumberLiteral < Literal
      def associated_type
        Type.new('number')
      end

      def eval(context)
        value.to_i
      end
    end

    class BoolLiteral < Literal
      def associated_type
        Type.new('bool')
      end

      def eval(context)
        value == 'true'
      end
    end
  end
end
