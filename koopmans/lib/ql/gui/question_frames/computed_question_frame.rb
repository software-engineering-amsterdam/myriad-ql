module QL
  module GUI
    class ComputedQuestionFrame < QuestionFrame
      def initialize(args)
        super
        @assignment = args[:assignment]
      end

      def reload
        super
        compute
      end

      def compute
        @value = @assignment.accept(Evaluator.new).value
        @widget.set_value(@value)
        store_value
      end
    end
  end
end