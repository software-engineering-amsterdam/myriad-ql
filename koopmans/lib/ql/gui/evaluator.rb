module QL
  module GUI
    class Evaluator
      def visit_expression_sequence(expression_sequence)
        expression_sequence.expressions.reduce do |left, operation|
          operation.accept(left, self)
        end
      end

      def visit_arithmetic_expression(left, binary_expression)
        AST::IntegerLiteral.new(evaluate_binary_expression(left, binary_expression))
      end

      def visit_boolean_expression(left, binary_expression)
        AST::BooleanLiteral.new(evaluate_binary_expression(left, binary_expression))
      end

      def visit_comparison_equal_expression(left, binary_expression)
        AST::BooleanLiteral.new(evaluate_binary_expression(left, binary_expression))
      end

      def visit_comparison_order_expression(left, binary_expression)
        AST::BooleanLiteral.new(evaluate_binary_expression(left, binary_expression))
      end

      def visit_integer_negation(integer_negation)
        AST::IntegerLiteral.new(evaluate_negation(integer_negation))
      end

      def visit_boolean_negation(boolean_negation)
        AST::BooleanLiteral.new(evaluate_negation(boolean_negation))
      end

      def visit_integer_literal(integer_literal)
        integer_literal
      end

      def visit_boolean_literal(boolean_literal)
        boolean_literal
      end

      def visit_string_literal(string_literal)
        string_literal
      end

      def visit_variable(variable)
        VariableTable.find(variable.name)
      end

      def evaluate_binary_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        eval("#{left.value} #{operator} #{right.value}")
      end

      def evaluate_negation(negation)
        operator = negation.operator
        expression = negation.expression.accept(self)
        eval("#{operator} #{expression.value}")
      end
    end
  end
end