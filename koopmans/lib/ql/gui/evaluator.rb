module QL
  module GUI
    class Evaluator
      # visit operation in expression
      def visit_expression(expression)
        if expression.expression.respond_to? :reduce
          expression.expression.reduce do |left, operation|
            operation.accept(left, self)
          end
        else
          expression.expression.accept(self)
        end
      end

      def visit_arithmetic_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        AST::IntegerLiteral.new(eval("#{left.value} #{operator} #{right.value}"))
      end

      def visit_boolean_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        AST::BooleanLiteral.new(eval("#{left.value} #{operator} #{right.value}"))
      end

      def visit_comparison_equal_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        AST::BooleanLiteral.new(eval("#{left.value} #{operator} #{right.value}"))
      end

      def visit_comparison_order_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        AST::BooleanLiteral.new(eval("#{left.value} #{operator} #{right.value}"))
      end

      def visit_integer_negation(integer_negation)
        operator = integer_negation.operator
        expression = integer_negation.expression.accept(self)
        AST::IntegerLiteral.new(eval("#{operator} #{expression.value}"))
      end

      def visit_boolean_negation(boolean_negation)
        operator = boolean_negation.operator
        expression = boolean_negation.expression.accept(self)
        AST::BooleanLiteral.new(eval("#{operator} #{expression.value}"))
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
    end
  end
end