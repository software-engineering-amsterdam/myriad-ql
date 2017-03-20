module QL
  module TypeChecker
    class OperandsTypeEvaluator
      def visit_form(form, collected_data)
        @variable_type = collected_data
        form.statements.map { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement, _)
        condition_type = if_statement.condition.accept(self)
        check_if_condition(if_statement, condition_type)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_if_else_statement(if_else_statement, _)
        condition_type = if_else_statement.condition.accept(self)
        check_if_condition(if_else_statement, condition_type)
        if_else_statement.if_body.map { |statement| statement.accept(self) }
        if_else_statement.else_body.map { |statement| statement.accept(self) }
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
        compatible_types = [AST::IntegerType, AST::MoneyType]
        return_type = AST::IntegerType.new
        evaluate_types(left, binary_expression, compatible_types, return_type)
      end

      def visit_boolean_expression(left, binary_expression)
        compatible_types = [AST::BooleanType]
        return_type = AST::BooleanType.new
        evaluate_types(left, binary_expression, compatible_types, return_type)
      end

      def visit_comparison_equal_expression(left, binary_expression)
        compatible_types = [AST::BooleanType, AST::IntegerType, AST::MoneyType, AST::StringType]
        return_type = AST::BooleanType.new
        evaluate_types(left, binary_expression, compatible_types, return_type)
      end

      def visit_comparison_order_expression(left, binary_expression)
        compatible_types = [AST::IntegerType, AST::MoneyType]
        return_type = AST::BooleanType.new
        evaluate_types(left, binary_expression, compatible_types, return_type)
      end

      def visit_boolean_negation(integer_negation)
        compatible_types = [AST::BooleanType]
        evaluate_type(integer_negation, compatible_types)
      end

      def visit_integer_negation(boolean_negation)
        compatible_types = [AST::IntegerType, AST::MoneyType]
        evaluate_type(boolean_negation, compatible_types)
      end

      def visit_boolean_type(boolean_type)
        boolean_type
      end

      def visit_date_type(date_type)
        date_type
      end

      def visit_decimal_type(decimal_type)
        decimal_type
      end

      def visit_error_type(error_type)
        error_type
      end

      def visit_integer_type(integer_type)
        integer_type
      end

      def visit_money_type(money_type)
        money_type
      end

      def visit_string_type(string_type)
        string_type
      end

      def visit_boolean_literal(_)
        AST::BooleanType.new
      end

      def visit_integer_literal(_)
        AST::IntegerType.new
      end

      def visit_string_literal(_)
        AST::StringType.new
      end

      def visit_variable(variable)
        @variable_type[variable.name]
      end

      def check_if_condition(if_statement, condition_type)
        return if condition_type.is_a?(AST::BooleanType)
        NotificationTable.store(Notification::Error.new("#{if_statement.condition} is not of the type boolean"))
      end

      def evaluate_types(left, binary_expression, compatible_types, return_type)
        left_type = left.accept(self)
        right_type = binary_expression.expression.accept(self)
        if check_compatibility(left_type, compatible_types) && check_compatibility(right_type, compatible_types)
          return_type
        else
          NotificationTable.store(Notification::Error.new("incompatible types at #{binary_expression.operator} operator"))
          AST::ErrorType.new
        end
      end

      def evaluate_type(negation, compatible_types)
        expression_type = negation.expression.accept(self)
        if check_compatibility(expression_type, compatible_types)
          expression_type
        else
          NotificationTable.store(Notification::Error.new("incompatible types at #{negation.operator} negation"))
          AST::ErrorType.new
        end
      end

      def check_compatibility(type, compatible_types)
        compatible_types.include?(type.class)
      end
    end
  end
end
