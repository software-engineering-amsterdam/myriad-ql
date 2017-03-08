module QL
  module Visitor
    class BaseCollector
      # gather all labels from all questions and check for duplicates
      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }
      end

      # visit all statements of the if block
      def visit_if_statement(if_statement)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_question(question)
        question
      end

      # visit the calculations of both the left and right sides
      def visit_expression(expression)
        [expression.left.accept(self), expression.right.accept(self)]
      end

      def visit_variable(variable)
        [variable]
      end

      # nothing has to be done with a literal
      def visit_literal(_)
      end

      # visit the calculation of the negation expression
      def visit_negation(negation)
        negation.expression.accept(self)
      end
    end
  end
end
