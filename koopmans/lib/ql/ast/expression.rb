module QL
  module AST
    class Expression
      include Notification

      attr_reader :expression

      def initialize(expression)
        @expression = expression
      end

      # makes sure all (sub)expressions are calculated in correct order
      def eval
        expression.reduce { |left, operation| operation.call(left) }.eval
      end

      def eval_type
        expression.reduce { |left, operation| operation.type_check(left) }
      end

      def accept(visitor)
        # expression.reduce do |left, operation|
        #   left.accept(visitor)
        #   operation.accept(visitor)
        # end
        visitor.visit_expression(self)
        # expression.reduce { |left, operation| operation.call(left) }
      end

      def accept_types
        [BooleanType, IntegerType, MoneyType]
      end
    end

    class Negation < Expression
      def accept(visitor)
        visitor.visit_negation(self)
      end
    end

    class BooleanNegation < Negation
      def eval
        BooleanLiteral.new(!expression.eval.to_value)
      end

      def accept_types
        [BooleanType]
      end
    end

    class IntegerNegation < Negation
      def eval
        IntegerLiteral.new(-expression.eval.to_value)
      end

      def accept_types
        [IntegerType, MoneyType]
      end
    end

    class BinaryExpression < Expression
      def call(left)
        pp left
        pp self.expression
        left  = left.eval
        right = self.expression.eval
        self.eval(left.to_value, right.to_value)
      end

      def type_check(left)
        pp 'type checking'
        pp left
        pp self
        pp self.expression #right side
        pp 'calculating'
        left  = left.eval_type
        right = self.expression.eval_type
        pp left
        pp right
        pp self
        self.eval_type(left, right)
      end

      def accept(visitor)
        visitor.visit_binary_expression(self)
      end
    end

    # booleans: && ||
    class BooleanExpression < BinaryExpression
      def accept_types
        [BooleanType]
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
      def accept_types
        [IntegerType, MoneyType]
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

      def eval_type(left, right)
        pp 'add'
        pp left
        pp right
        if left != right
          Error.new('je hebt een fout gemaakt')
        else
          nil
        end
      end

      def accept(visitor)
        visitor.visit_add(self)
      end
    end

    class Multiply < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left * right)
      end

      def eval_type(left, right)
        pp 'multiply'
        pp left
        pp right
        if left != right
          Error.new('je hebt een fout gemaakt')
        else
          left
        end
      end
    end

    class Divide < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left / right)
      end
    end

    # comparisons == !=
    class ComparisonEqual < BinaryExpression
      def accept_types
        [BooleanType, IntegerType, StringType, MoneyType]
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
      def accept_types
        [IntegerType, MoneyType]
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
