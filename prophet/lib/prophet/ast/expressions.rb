module Prophet
  module Ast
    class Expression < Node
      def eval(context)
      end
    end

    class BinaryExpression < Expression.new(:left, :right)
    end

    class LogicalAnd < BinaryExpression
    end

    class LogicalOr < BinaryExpression
    end

    class Equal < BinaryExpression
    end

    class NotEqual < BinaryExpression
    end

    class LessThenOrEqual < BinaryExpression
    end

    class LessThen < BinaryExpression
    end

    class GreaterThen < BinaryExpression
    end

    class GreaterThenOrEqual < BinaryExpression
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

    class UnaryExpression < Expression.new(:value)
      def eval(context)
        value.eval(context)
      end
    end

    class Negation < UnaryExpression
    end
  end
end
