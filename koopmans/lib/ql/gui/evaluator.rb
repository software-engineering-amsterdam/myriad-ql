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
      def visit_binary_expression(left, binary_expression)
        left  = left.accept(self)
        right = binary_expression.expression.accept(self)
        binary_expression.eval(left.to_value, right.to_value)
      end

      def visit_negation(negation)
        expression = negation.expression.accept(self)
        negation.eval(expression.to_value)
      end

      def visit_literal(literal)
        literal
      end


      def visit_variable(variable)
        VariableTable.find(variable.name)
      end
    end
  end
end