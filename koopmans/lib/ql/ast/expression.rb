require 'parslet'
module QL
  module AST
    class Expression
      def self.includes_type?(type)
        !([type].flatten & accept_types).empty?
      end

      def accept(visitor)
        visitor.visit_expression(self)
      end
    end

    class Negation < Expression
      attr_accessor :expression

      def initialize(expression)
        @expression = expression
      end

      def accept(visitor)
        visitor.visit_negation(self)
      end
    end

    class BooleanNegation < Negation
      def eval
        !expression.eval
      end

      def self.accept_types
        [BooleanType]
      end
    end

    class IntegerNegation < Negation
      def eval
        0 - expression.eval
      end

      def self.accept_types
        [IntegerType, MoneyType]
      end
    end

    class BinaryExpression < Expression
      attr_accessor :left, :right

      def initialize(left, right)
        @left = left
        @right = right
      end
    end

    # booleans: && ||
    class BooleanExpression < BinaryExpression
      def self.accept_types
        [BooleanType]
      end
    end

    class And < BooleanExpression
      def eval
        left.eval && right.eval
      end
    end

    class Or < BooleanExpression
      def eval
        left.eval || right.eval
      end
    end

    # arithmetic: - + * /
    class ArithmeticExpression < BinaryExpression
      def self.accept_types
        [IntegerType, MoneyType]
      end
    end

    class Subtract < ArithmeticExpression
      def eval
        left.eval - right.eval
      end
    end

    class Add < ArithmeticExpression
      def eval
        left.eval + right.eval
      end
    end

    class Multiply < ArithmeticExpression
      def eval
        left.eval * right.eval
      end
    end

    class Divide < ArithmeticExpression
      def eval
        left.eval / right.eval
      end
    end

    # comparisons == !=
    class ComparisonEqual < BinaryExpression
      def self.accept_types
        [BooleanType, IntegerType, StringType, MoneyType]
      end
    end

    class Equal< ComparisonEqual
      def eval
        left.eval == right.eval
      end
    end

    class NotEqual < ComparisonEqual
      def eval
        left.eval != right.eval
      end
    end

    # comparisons: < > <= >=
    class ComparisonOrdering < BinaryExpression
      def self.accept_types
        [IntegerType, MoneyType]
      end
    end

    class Less < ComparisonOrdering
      def eval
        left.eval < right.eval
      end
    end

    class Greater < ComparisonOrdering
      def eval
        left.eval > right.eval
      end
    end

    class LessEqual < ComparisonOrdering
      def eval
        left.eval <= right.eval
      end
    end

    class GreaterEqual < ComparisonOrdering
      def eval
        left.eval >= right.eval
      end
    end
  end
end