module QL
  module GUI
    class BooleanQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
        super
        @variable.value = true
        @variable.type  = BooleanType

        RadioWidget.new(question_frame: self, true_value: 'JAAAA', false_value: 'NEEEE')
        # CheckboxWidget.new(question: self)
        # DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
      end
    end
  end
end

