module QL
  module GUI
    class BooleanQuestionFrame < QuestionFrame
      include AST

      def initialize(gui, question, condition=nil)
        super
        # @variable.value = true
        # @variable.type  = BooleanType

        @widget = RadioWidget.new(question_frame: self, true_value: 'JAAAA', false_value: 'NEEEE')
        # CheckboxWidget.new(question: self)
        # DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
        # store
      end

      # def store_in_question_table(value)
      #   QuestionTable.store(question.variable.name, BooleanLiteral.new(value))
      #   p question.variable.name
      #   p value
      # end

      # def store
      #   literal_type = question.type.literal_type
      #   QuestionTable.store(question.variable.name, literal_type.new(@widget.value))
      #   gui.reload_questions
      # end

      # def value_changed
      #   store(@widget.value)
      #   QuestionTable.store(question.variable.name, BooleanLiteral.new(value))
      # end
    end
  end
end

