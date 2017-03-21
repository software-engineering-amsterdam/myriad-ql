module QL
  module GUI
    class RadioWidget < Widget
      def initialize(true_label='True', false_label='False')
        @default_value = false
        @true_label = true_label
        @false_label = false_label
      end

      def render(tk_frame)
        shared_variable = TkVariable.new(@default_value)

        radio_button = TkRadioButton.new(tk_frame).pack
        radio_button.text = @true_label
        radio_button.value = true
        radio_button.variable = shared_variable
        radio_button.command = proc { callback(shared_variable.bool) }

        radio_button = TkRadioButton.new(tk_frame).pack
        radio_button.text = @false_label
        radio_button.value = false
        radio_button.variable = shared_variable
        radio_button.command = proc { callback(shared_variable.bool) }
      end
    end
  end
end
