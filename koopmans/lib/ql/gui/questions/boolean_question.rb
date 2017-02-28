module QL
  module GUI
    class BooleanQuestion < Question
      include AST

      def initialize(args)
        super
        @variable.value = (true)
        @variable.type  = BooleanType
        create_radio_button('Yes', true)
        create_radio_button('No', false)
      end

      def create_radio_button(text, value)
        radio_button          = TkRadioButton.new(@frame).pack
        radio_button.variable = @variable
        radio_button.command  = proc { @gui.value_changed }
        radio_button.text     = text
        radio_button.value    = value
      end
    end
  end
end

# module QL
#   module GUI
#     class BooleanQuestion < Question
#       include AST
#
#       def initialize(args)
#         super
#         @variable.value = (true)
#         @variable.type  = BooleanType
#         RadioWidget.new(self)
#       end
#     end
#
#     class RadioWidget
#       attr_accessor :question
#
#       def initialize(question)
#         @question = question
#         create_radio_button('Yes', true)
#         create_radio_button('No', false)
#       end
#
#       def create_radio_button(text, value)
#         radio_button          = TkRadioButton.new(@question.frame).pack
#         radio_button.variable = @question.variable
#         radio_button.command  = proc { @question.gui.value_changed }
#         radio_button.text     = text
#         radio_button.value    = value
#       end
#     end
#   end
# end
#
