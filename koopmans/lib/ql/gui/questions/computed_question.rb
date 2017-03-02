module QL
  module GUI
    class ComputedQuestion < Question
      attr_accessor :calculation

      def initialize(args)
        super
        @calculation   = args[:calculation]
        @variable.type = args[:type]
        ComputedWidget.new(question: self)
        calculate
      end

      def reload
        super
        calculate
      end

      def calculate
        @variable.value = eval(@calculation.eval) if @calculation
      end
    end
  end
end