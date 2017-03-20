module QL
  module TypeChecker
    class QuestionCollector
      def visit_form(form, _)
        form.statements.map { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement, _)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_if_else_statement(if_else_statement, _)
        if_body_questions = if_else_statement.if_body.map { |statement| statement.accept(self) }
        else_body_questions = if_else_statement.else_body.map { |statement| statement.accept(self) }
        [if_body_questions, else_body_questions]
      end

      def visit_question(question, _)
        question
      end

      def visit_computed_question(computed_question, _)
        computed_question
      end
    end
  end
end
