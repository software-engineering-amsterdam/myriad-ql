module QL
  module TypeChecker
    class Evaluator
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
        negation.expression.eval_type
      end

      # visit operation in expression
      def visit_expression(expression)
        expression.expression.reduce do |left, operation|
          pp operation.accept(left, self)
        end
      end

      def visit_literal(literal)
        literal
      end

      # visit both left and right sides of binary expression and perform calculation
      # they can be for example literal, variable or another binary expression
      def visit_binary_expression(left, binary_expression)
        pp '--------------------------------'
        left  = left.accept(self)
        right = binary_expression.expression.accept(self)
        binary_expression.eval(left.to_value, right.to_value)
      end

      def visit_variable(_)
        # TODO do variable lookup
      end
    end
  end
end