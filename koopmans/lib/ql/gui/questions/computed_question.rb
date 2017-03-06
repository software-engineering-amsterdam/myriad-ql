module QL
  module GUI
    class ComputedQuestion < Question
      attr_accessor :calculation

      def initialize(args)
        super
        question = args[:question]
        # @calculation   = args[:calculation]
        # @variable.type = args[:type]
        @calculation   = question.assignment
        @variable.type = question.type
        ComputedWidget.new(question: self)
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