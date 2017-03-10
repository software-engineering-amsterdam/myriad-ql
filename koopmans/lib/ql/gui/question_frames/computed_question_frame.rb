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
        literal_type = question.type.literal_type
        # value = question.accept(TypeChecker::Evaluator.new).to_value
        value = @question.assignment.accept(TypeChecker::Evaluator.new).to_value
        @widget.set_value(value)
        QuestionTable.store(question.variable.name, literal_type.new(value))
      end
    end
  end
end