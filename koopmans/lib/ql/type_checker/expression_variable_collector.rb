module QL
  module TypeChecker
    class ExpressionVariableCollector
      def visit_form(form, collected_data=nil)
        form.statements.map { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement)
        if_condition_variables = if_statement.condition.accept(self)
        if_body_variables = if_statement.body.map { |statement| statement.accept(self) }
        [if_condition_variables, if_body_variables]
      end

      def visit_if_else_statement(if_else_statement)
        if_condition_variables = if_else_statement.condition.accept(self)
        if_body_variables = if_else_statement.if_body.map { |statement| statement.accept(self) }
        else_body_variables = if_else_statement.else_body.map { |statement| statement.accept(self) }
        [if_condition_variables, if_body_variables, else_body_variables]
      end

      def visit_question(_)
      end

      def visit_computed_question(computed_question)
        computed_question.assignment.accept(self)
      end

      def visit_expression_sequence(expression_sequence)
        expression_sequence.expressions.reduce do |left, operation|
          operation.accept(left, self)
        end
      end

      def visit_arithmetic_expression(left, binary_expression)
        left = try_accept(left)
        right = try_accept(binary_expression.expression)
        [left, right]
      end

      def visit_boolean_expression(left, binary_expression)
        left = try_accept(left)
        right = try_accept(binary_expression.expression)
        [left, right]
      end

      def visit_comparison_equal_expression(left, binary_expression)
        left = try_accept(left)
        right = try_accept(binary_expression.expression)
        [left, right]
      end

      def visit_comparison_order_expression(left, binary_expression)
        left = try_accept(left)
        right = try_accept(binary_expression.expression)
        [left, right]
      end

      def visit_boolean_negation(integer_negation)
        integer_negation.expression.accept(self)
      end

      def visit_integer_negation(boolean_negation)
        boolean_negation.expression.accept(self)
      end

      def visit_integer_literal(_)
        []
      end

      def visit_boolean_literal(_)
        []
      end

      def visit_string_literal(_)
        []
      end

      def visit_variable(variable)
        [variable]
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