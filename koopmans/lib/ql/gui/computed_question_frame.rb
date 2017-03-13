module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      def render(gui, position)
        super
      end

      def create_widget
        @widget = ComputedWidget.new(@tk_frame)
      end

      def reload
        super
        compute
      end

      def compute
        @value = assignment.accept(Evaluator.new).to_value
        @widget.set_value(@value)
        store_value
      end

      # helpers
      def assignment
        @ast_question.assignment
      end
    end
  end
end