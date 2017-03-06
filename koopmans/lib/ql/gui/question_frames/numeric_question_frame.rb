module QL
  module GUI
    class NumericQuestionFrame < QuestionFrame
      include AST

      def initialize(args)
        super
        @variable.value = 0
        @variable.type  = IntegerType

        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        SpinboxWidget.new(question_frame: self)
        # TextWidget.new(question_frame: self)
      end
    end
  end
end