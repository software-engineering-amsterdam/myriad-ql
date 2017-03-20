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

      # visit both left and right sides of binary expression and perform calculation
      # they can be for example literal, variable or another binary expression
      # def visit_binary_expression(left, binary_expression)
      #   left = left.accept(self)
      #   right = binary_expression.expression.accept(self)
      #   binary_expression.eval(left.to_value, right.to_value)
      # end

      def visit_arithmetic_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        operator = binary_expression.operator
        AST::IntegerLiteral.new(eval("#{left.value} #{operator} #{right.value}"))
      end

      # def visit_negation(negation)
      #   expression = negation.expression.accept(self)
      #   negation.eval(expression.to_value)
      # end

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

def to_boolean(value)
  return true if value == 'true'
  return false if value == 'false'
end