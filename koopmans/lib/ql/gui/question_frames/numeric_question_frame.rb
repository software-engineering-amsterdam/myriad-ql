module QL
  module GUI
    class NumericQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
        super
        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        @widget = SpinboxWidget.new(question_frame: self)
        # TextWidget.new(question_frame: self)
      end

      def store_in_question_table(value)
        QuestionTable.store(question.variable.name, IntegerLiteral.new(value))
      end

    end
  end
end