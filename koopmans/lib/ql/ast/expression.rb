module QL
  module AST
    class Expression
      include Notification

      attr_reader :expression

      def initialize(expression)
        @expression = expression
      end

      def accept(visitor)
        visitor.visit_expression(self)
      end

      def eval_type(left_type, right_type)
        # return the left type if there are no errors and else return an error
        if self.is_compatible_with.include?(left_type) and self.is_compatible_with.include?(right_type) and left_type == right_type
          left_type
        else
          NotificationTable.store(Error.new("incompatible types at #{self}"))
          ErrorType.new
        end
      end
    end

    class Negation < Expression
      def accept(visitor)
        visitor.visit_negation(self)
      end

      def eval_type(expression)
        if self.is_compatible_with.include?(expression)
          expression
        else
          NotificationTable.store(Error.new("incompatible types at #{self}"))
        end
      end
    end

    class BooleanNegation < Negation
      def eval(expression)
        BooleanLiteral.new(!expression)
      end

      def is_compatible_with
        [BooleanType.new]
      end
    end

    class IntegerNegation < Negation
      def eval(expression)
        IntegerLiteral.new(-expression)
      end

      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end
    end

    class BinaryExpression < Expression
      def accept(left, visitor)
        visitor.visit_binary_expression(left, self)
      end
    end

    # booleans: && ||
    class BooleanExpression < BinaryExpression
      def is_compatible_with
        [BooleanType.new]
      end
    end

    class And < BooleanExpression
      def eval(left, right)
        BooleanLiteral.new(left && right)
      end
    end

    class Or < BooleanExpression
      def eval(left, right)
        BooleanLiteral.new(left || right)
      end
    end

    # arithmetic: - + * /
    class ArithmeticExpression < BinaryExpression
      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end
    end

    class Subtract < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left - right)
      end
    end

    class Add < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left + right)
      end
    end

    class Multiply < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left * right)
      end
    end

    class Divide < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left / right)
      end
    end

    # comparisons == !=
    class ComparisonEqual < BinaryExpression
      def is_compatible_with
        [BooleanType.new, IntegerType.new, StringType.new, MoneyType.new]
      end
    end

    class Equal < ComparisonEqual
      def eval(left, right)
        BooleanLiteral.new(left == right)
      end
    end

    class NotEqual < ComparisonEqual
      def eval(left, right)
        BooleanLiteral.new(left != right)
      end
    end

    # comparisons: < > <= >=
    class ComparisonOrdering < BinaryExpression
      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end
    end

    class Less < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left < right)
      end
    end

    class Greater < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left > right)
      end
    end

    class LessEqual < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left <= right)
      end
    end

    class GreaterEqual < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left >= right)
      end
    end
  end
end
