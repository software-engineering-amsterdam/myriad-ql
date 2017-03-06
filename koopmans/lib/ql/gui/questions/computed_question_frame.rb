module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      attr_accessor :calculation

      def initialize(args)
        super
        @calculation   = @question.assignment
        @variable.type = @question.type
        ComputedWidget.new(question_frame: self)
        calculate
      end

      def reload
        super
        calculate
      end

      def calculate
        @variable.value = eval(@calculation.eval.to_s) if @calculation
      end
    end
  end
end