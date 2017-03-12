module Prophet
  module Ast
    class Expression < Node
      def eval(context)
      end
    end

    class UnaryExpression < Expression.new(:value)
      def eval(context)
        value.eval(context)
      end
    end

    class BinaryExpression < Expression.new(:left, :right)
    end

    class Multiplication < BinaryExpression
      def eval(context)
        left.eval(context) * right.eval(context)
      end
    end

    class Division < BinaryExpression
      def eval(context)
        left.eval(context) / right.eval(context)
      end
    end

    class Addition < BinaryExpression
      def eval(context)
        left.eval(context) + right.eval(context)
      end
    end

    class Subtraction < BinaryExpression
      def eval(context)
        left.eval(context) - right.eval(context)
      end
    end
  end
end
