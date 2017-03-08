module QL
  module AST
    class Expression
      def accept(visitor)
        visitor.visit_expression(self)
      end
    end

    class Sequence
      attr_reader :sequence

      def initialize(sequence)
        @sequence = sequence
      end

      # makes sure all sequences are calculated in correct order
      def eval
        sequence.reduce { |left, operation| operation.call(left) }.eval
      end
    end

    class Negation < Expression
      attr_accessor :single

      def initialize(single)
        @single = single
      end

      def accept(visitor)
        visitor.visit_negation(self)
      end
    end

    class BooleanNegation < Negation
      def eval
        BooleanLiteral.new(!single.eval.to_value)
      end

      def accept_types
        [BooleanType]
      end
    end

    class IntegerNegation < Negation
      def eval
        IntegerLiteral.new(-single.eval.to_value)
      end

      def accept_types
        [IntegerType, MoneyType]
      end
    end

    class BinaryExpression < Expression
      attr_accessor :right

      def initialize(right)
        @right = right
      end

      def call(left)
        left  = left.eval
        right = self.right.eval
        to_corresponding_literal(self.eval(left.to_value, right.to_value))
      end
    end

    # booleans: && ||
    class BooleanExpression < BinaryExpression
      def accept_types
        [BooleanType]
      end

      def to_corresponding_literal(result)
        BooleanLiteral.new(result)
      end
    end

    class And < BooleanExpression
      def eval(left, right)
        left && right
      end
    end

    class Or < BooleanExpression
      def eval(left, right)
        left || right
      end
    end

    # arithmetic: - + * /
    class ArithmeticExpression < BinaryExpression
      def accept_types
        [IntegerType, MoneyType]
      end

      def to_corresponding_literal(result)
        IntegerLiteral.new(result)
      end
    end

    class Subtract < ArithmeticExpression
      def eval(left, right)
        left - right
      end
    end

    class Add < ArithmeticExpression
      def eval(left, right)
        left + right
      end
    end

    class Multiply < ArithmeticExpression
      def eval(left, right)
        left * right
      end
    end

    class Divide < ArithmeticExpression
      def eval(left, right)
        left / right
      end
    end

    # comparisons == !=
    class ComparisonEqual < BinaryExpression
      def accept_types
        [BooleanType, IntegerType, StringType, MoneyType]
      end

      def to_corresponding_literal(result)
        BooleanLiteral.new(result)
      end
    end

    class Equal < ComparisonEqual
      def eval(left, right)
        left == right
      end
    end

    class NotEqual < ComparisonEqual
      def eval(left, right)
        left != right
      end
    end

    # comparisons: < > <= >=
    class ComparisonOrdering < BinaryExpression
      def accept_types
        [IntegerType, MoneyType]
      end

      def to_corresponding_literal(result)
        BooleanLiteral.new(result)
      end
    end

    class Less < ComparisonOrdering
      def eval(left, right)
        left < right
      end
    end

    class Greater < ComparisonOrdering
      def eval(left, right)
        left > right
      end
    end

    class LessEqual < ComparisonOrdering
      def eval(left, right)
        left <= right
      end
    end

    class GreaterEqual < ComparisonOrdering
      def eval(left, right)
        left >= right
      end
    end
  end
end
