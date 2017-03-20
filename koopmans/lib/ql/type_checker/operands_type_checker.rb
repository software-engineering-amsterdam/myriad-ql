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

        if_body_types = if_else_statement.if_body.map { |statement| statement.accept(self) }
        else_body_types = if_else_statement.else_body.map { |statement| statement.accept(self) }
        [if_body_types, else_body_types]
      end

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

      # visit both left and right sides of binary expression and perform type check
      # they can be for example BooleanType, IntegerType, StringType or ErrorType
      # def visit_binary_expression(left, binary_expression)
      #   left = left.accept(self)
      #   right = binary_expression.expression.accept(self)
      #   binary_expression.eval_type(left, right)
      # end

      def visit_arithmetic_expression(left, binary_expression)
        left = left.accept(self)
        right = binary_expression.expression.accept(self)
        evaluate_types(left, right, [AST::IntegerType, AST::MoneyType], AST::IntegerType.new)
      end

      # def visit_negation(negation)
      #   expression = negation.expression.accept(self)
      #   negation.eval_type(expression)
      # end

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

      def visit_type(type)
        type
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