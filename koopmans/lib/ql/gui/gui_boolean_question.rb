module QL
  module GUI
    class GUIBooleanQuestion < GUIQuestion
      include AST
      def initialize(args)
        super
        @variable.value = (true)
        @variable.type = BooleanType
        create_radio_button('Yes', true)
        create_radio_button('No', false)
      end

      def create_radio_button(text, value)
        radio_button = TkRadioButton.new(@frame).pack
        radio_button.variable = @variable
        radio_button.command = proc { @gui.value_changed }
        radio_button.text = text
        radio_button.value = value
      end
    end
  end
end