module QL
  module GUI
    class StringQuestionFrame < QuestionFrame
      include AST

      def initialize(args)
        super
        @variable.value = ("")
        # @variable.type  = StringType

        @widget = TextWidget.new(question_frame: self)
      end
    end
  end
end