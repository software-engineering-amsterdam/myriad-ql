module QL
  module Visitor
    class QuestionCollector
      # visit all statements of the form
      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }
      end

      # visit all statements of the if block
      def visit_if_statement(if_statement)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      # return question
      def visit_question(question)
        question
      end

      # return computed question
      def visit_computed_question(computed_question)
        computed_question
      end
    end
  end
end
