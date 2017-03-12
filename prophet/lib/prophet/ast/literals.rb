module Prophet
  module Ast
    class Literal < Node.new(:value)
    end

    class TextLiteral < Literal
      def eval(context)
        value.to_s
      end
    end

    class NumberLiteral < Literal
      def eval(context)
        value.to_i
      end
    end

    class BoolLiteral < Literal
      def eval(context)
        value == 'true'
      end
    end
  end
end
