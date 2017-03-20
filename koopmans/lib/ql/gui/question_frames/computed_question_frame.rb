module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      def create_corresponding_widget
        @widget = ComputedWidget.new(@tk_frame)
      end

      def reload
        super
        compute
      end

      def compute
        @value = assignment.accept(Evaluator.new).value
        @widget.set_value(@value)
        store_value
      end

      # TODO move to ast/question
      def assignment
        @ast_question.assignment
      end
    end
  end
end