module QL
  module TypeChecker
    class QuestionCollector
      def visit_form(form, collected_data=nil)
        form.statements.map { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_question(question)
        question
      end

      def visit_computed_question(computed_question)
        computed_question
      end
    end
  end
end
