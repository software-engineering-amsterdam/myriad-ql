module Prophet
  module Ast
    class Expression < Node
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
    end

    class Subtraction < BinaryExpression
    end

    class Multiplication < BinaryExpression
    end

    class Division < BinaryExpression
    end

    class UnaryExpression < Expression.new(:value)
    end

    class Negation < UnaryExpression
    end
  end
end
