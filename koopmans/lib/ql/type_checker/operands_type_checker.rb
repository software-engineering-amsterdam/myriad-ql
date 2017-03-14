module QL
  module TypeChecker
    class OperandsTypeChecker
      include AST
      include Notification

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
        # check if if condition is of boolean type
        check_if_condition(if_statement, condition_type)
        if_statement.body.map { |statement| statement.accept(self) }
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
      def visit_binary_expression(left, binary_expression)
        left  = left.accept(self)
        right = binary_expression.expression.accept(self)
        binary_expression.eval_type(left, right)
      end

      def visit_negation(negation)
        expression = negation.expression.accept(self)
        negation.eval_type(expression)
      end

      def visit_literal(literal)
        literal.to_type
      end

      def visit_type(type)
        type
      end

      def visit_variable(variable)
        @variable_types[variable.name]
      end

      protected
      def check_if_condition(if_statement, condition_type)
        unless condition_type.is_a?(BooleanType)
          NotificationTable.store(Error.new("#{if_statement.condition} is not of the type boolean"))
        end
      end
    end
  end
end