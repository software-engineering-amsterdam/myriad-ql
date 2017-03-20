module QL
  module TypeChecker
    class OperandsTypeChecker
      def visit_form(form, collected_data=nil)
        @variable_types = collected_data
        form.statements.map { |statement| statement.accept(self) }
      end

      # nothing has to be done with a question
      def visit_question(_)
      end

      def visit_computed_question(computed_question)
        computed_question.assignment.accept(self)
      end

      def visit_if_statement(if_statement)
        condition_type = if_statement.condition.accept(self)
        check_if_condition(if_statement, condition_type)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_if_else_statement(if_else_statement)
        condition_type = if_else_statement.condition.accept(self)
        check_if_condition(if_else_statement, condition_type)

        if_else_statement.if_body.map { |statement| statement.accept(self) }
        if_else_statement.else_body.map { |statement| statement.accept(self) }
      end

      # visit operation in expression
      def visit_expression(expression)
        expression.expression.reduce do |left, operation|
          operation.accept(left, self)
        end
      end

      def visit_arithmetic_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        evaluate_types(left, right, [AST::IntegerType, AST::MoneyType], AST::IntegerType.new)
      end

      def visit_boolean_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        evaluate_types(left, right, [AST::BooleanType], AST::BooleanType.new)
      end

      def visit_comparison_equal_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        evaluate_types(left, right, [AST::BooleanType, AST::IntegerType, AST::MoneyType, AST::StringType], AST::BooleanType.new)
      end

      def visit_comparison_order_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        evaluate_types(left, right, [AST::IntegerType, AST::MoneyType], AST::BooleanType.new)
      end


      def visit_boolean_negation(integer_negation)
        expression_type = integer_negation.expression.accept(self)
        evaluate_type(expression_type, [AST::BooleanType])
      end

      def visit_integer_negation(boolean_negation)
        expression_type = boolean_negation.expression.accept(self)
        evaluate_type(expression_type, [AST::IntegerType, AST::MoneyType])
      end

      # def visit_literal(literal)
      #   literal.to_type
      # end

      def visit_integer_literal(_)
        AST::IntegerType.new
      end

      def visit_boolean_literal(_)
        AST::BooleanType.new
      end

      def visit_string_literal(_)
        AST::StringType.new
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

      def visit_integer_type(integer_type)
        integer_type
      end

      def visit_money_type(money_type)
        money_type
      end

      def visit_string_type(string_type)
        string_type
      end

      def visit_variable(variable)
        @variable_types[variable.name]
      end

      # check if if condition is of boolean type
      def check_if_condition(if_statement, condition_type)
        unless condition_type.is_a?(AST::BooleanType)
          NotificationTable.store(Notification::Error.new("#{if_statement.condition} is not of the type boolean"))
        end
      end

      def evaluate_types(left_type, right_type, compatible_types, return_type)
        # return the left type if there are no errors and else return an error
        if check_compatibility(left_type, compatible_types) and check_compatibility(right_type, compatible_types)
          return_type
        else
          #TODO fix error msg
          NotificationTable.store(Notification::Error.new("1incompatible types at #{self}"))
          AST::ErrorType.new
        end
      end

      def evaluate_type(expression_type, compatible_types)
        if check_compatibility(expression_type, compatible_types)
          expression_type
        else
          #TODO fix error msg
          NotificationTable.store(Notification::Error.new("2incompatible types at #{self}"))
        end
      end

      def check_compatibility(type, compatible_types)
        compatible_types.include?(type.class)
      end
    end
  end
end