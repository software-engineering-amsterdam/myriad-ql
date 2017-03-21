module QL
  module TypeChecker
    class ExpressionVariableCollector
      attr_reader :variables

      def initialize
        @variables = []
      end

      def visit_form(form)
        form.statements.each { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement, _)
        if_statement.condition.accept(self)
        if_statement.body.each { |statement| statement.accept(self) }
      end

      def visit_if_else_statement(if_else_statement, _)
        if_else_statement.condition.accept(self)
        if_else_statement.if_body.each { |statement| statement.accept(self) }
        if_else_statement.else_body.each { |statement| statement.accept(self) }
      end

      def visit_question(_, _) end

      def visit_computed_question(computed_question, _)
        computed_question.assignment.accept(self)
      end

      def visit_expression_sequence(expression_sequence)
        expression_sequence.expressions.reduce do |left, operation|
          operation.accept(left, self)
        end
      end

      def visit_arithmetic_expression(left, binary_expression)
        visit_binary_expression_variables(left, binary_expression)
      end

      def visit_boolean_expression(left, binary_expression)
        visit_binary_expression_variables(left, binary_expression)
      end

      def visit_comparison_equal_expression(left, binary_expression)
        visit_binary_expression_variables(left, binary_expression)
      end

      def visit_comparison_order_expression(left, binary_expression)
        visit_binary_expression_variables(left, binary_expression)
      end

      def visit_boolean_negation(integer_negation)
        visit_negation_variables(integer_negation)
      end

      def visit_integer_negation(boolean_negation)
        visit_negation_variables(boolean_negation)
      end

      def visit_integer_literal(_) end

      def visit_boolean_literal(_) end

      def visit_string_literal(_) end

      def visit_variable(variable)
        @variables << variable
      end

      def visit_binary_expression_variables(left, binary_expression)
        try_accept(left)
        try_accept(binary_expression.expression)
      end

      def visit_negation_variables(negation)
        negation.expression.accept(self)
      end

      def try_accept(node)
        if node.respond_to?(:accept)
          node.accept(self)
        else
          node
        end
      end
    end
  end
end
