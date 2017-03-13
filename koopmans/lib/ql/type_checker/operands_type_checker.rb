module QL
  module TypeChecker
    class OperandsTypeChecker
      include Visitor
      include AST
      include Notification

      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }
      end

      # nothing has to be done with a question
      def visit_question(_)
      end

      # visit the assignment of a computed question
      def visit_computed_question(computed_question)
        computed_question.assignment.accept(self)
      end

      # visit both the condition all statements of the if statement
      def visit_if_statement(if_statement)
        if_statement.condition.accept(self)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_negation(negation)
        expression = negation.expression.accept(self)
        negation.eval_type(expression)
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

      def visit_literal(literal)
        literal.to_type
      end

      def visit_type(type)
        type
      end

      # visit both left and right sides of binary expression and perform calculation
      # they can be for example literal, variable or another binary expression
      def visit_binary_expression(left, binary_expression)
        left  = left.accept(self)
        right = binary_expression.expression.accept(self)
        binary_expression.eval_type(left, right)
      end

      def visit_variable(variable)
        QuestionTable.find(variable.name)
      end
    end
  end
end