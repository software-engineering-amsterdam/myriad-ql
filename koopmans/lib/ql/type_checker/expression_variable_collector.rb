module QL
  module TypeChecker
    class ExpressionVariableCollector
      def visit_form(form, _)
        form.statements.map { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement, _)
        if_condition_variables = if_statement.condition.accept(self)
        if_body_variables = if_statement.body.map { |statement| statement.accept(self) }
        [if_condition_variables, if_body_variables]
      end

      def visit_if_else_statement(if_else_statement, _)
        if_condition_variables = if_else_statement.condition.accept(self)
        if_body_variables = if_else_statement.if_body.map { |statement| statement.accept(self) }
        else_body_variables = if_else_statement.else_body.map { |statement| statement.accept(self) }
        [if_condition_variables, if_body_variables, else_body_variables]
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
        collect_binary_expression_variables(left, binary_expression)
      end

      def visit_boolean_expression(left, binary_expression)
        collect_binary_expression_variables(left, binary_expression)
      end

      def visit_comparison_equal_expression(left, binary_expression)
        collect_binary_expression_variables(left, binary_expression)
      end

      def visit_comparison_order_expression(left, binary_expression)
        collect_binary_expression_variables(left, binary_expression)
      end

      def visit_boolean_negation(integer_negation)
        collect_negation_variables(integer_negation)
      end

      def visit_integer_negation(boolean_negation)
        collect_negation_variables(boolean_negation)
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

      def collect_binary_expression_variables(left, binary_expression)
        left = try_accept(left)
        right = try_accept(binary_expression.expression)
        [left, right]
      end

      def collect_negation_variables(negation)
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
