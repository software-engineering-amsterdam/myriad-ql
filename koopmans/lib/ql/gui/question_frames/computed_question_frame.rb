module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      include AST

      def initialize(question, condition=nil)
        super
      end

      def render(gui, position)
        super
        compute
      end

      def create_widget
        @widget = ComputedWidget.new(@frame)
      end

      def reload
        super
        compute
      end

      def compute
        value = assignment.accept(Evaluator.new).to_value
        @widget.set_value(value)
        store_value
      end

      def assignment
        ast_question.assignment
      end
    end
  end
end