module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame

      def initialize(gui, question, condition=nil)
        super
        @variable.type = @question.type
        ComputedWidget.new(question_frame: self)
        compute
      end

      def reload
        super
        compute
      end

      def compute
        @variable.value = eval(@question.assignment.eval.to_s) if @question.assignment
      end
    end
  end
end