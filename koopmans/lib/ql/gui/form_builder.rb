module QL
  module GUI
    class FormBuilder
      attr_accessor :question_frames

      def initialize(ast)
        @question_frames = []
        visit_form(ast)
      end

      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }.flatten
      end

      # stack if conditions if possible
      def visit_if_statement(if_statement, condition=nil)
        if condition
          condition = AST::Expression.new([condition, AST::And.new(if_statement.condition)])
        else
          condition = if_statement.condition
        end
        if_statement.body.map { |statement| statement.accept(self, condition) }
      end

      def visit_question(question, condition=nil)
        p condition
        @question_frames << QuestionFrame.new(question, condition)
      end

      def visit_computed_question(question, condition=nil)
        @question_frames << ComputedQuestionFrame.new(question, condition)
      end
    end
  end
end