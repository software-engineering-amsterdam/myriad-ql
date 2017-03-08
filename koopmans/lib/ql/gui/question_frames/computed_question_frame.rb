module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
        super
        compute
      end

      def set_widget
        @widget = ComputedWidget.new(self)
      end

      def reload
        super
        compute
      end

      def compute
        value = @question.assignment.eval.to_value
        @widget.set_value(value)
        # store
        QuestionTable.store(question.variable.name, IntegerLiteral.new(value))
      end
    end
  end
end