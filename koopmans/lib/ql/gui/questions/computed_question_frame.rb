module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame

      def initialize(args)
        super
        @variable.type = @question.type
        ComputedWidget.new(question_frame: self)
        calculate
      end

      def reload
        super
        calculate
      end

      def calculate
        @variable.value = eval(@question.assignment.eval.to_s) if @question.assignment
      end
    end
  end
end