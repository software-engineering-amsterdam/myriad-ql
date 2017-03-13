module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
        super
        compute
      end

      def create_widget
        @widget = ComputedWidget.new(self)
      end

      def reload
        super
        compute
      end

      def compute
        value = @question.assignment.accept(Evaluator.new).to_value
        @widget.set_value(value)
        store_value
      end
    end
  end
end