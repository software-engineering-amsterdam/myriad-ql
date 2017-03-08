module QL
  module GUI
    class NumericQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
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