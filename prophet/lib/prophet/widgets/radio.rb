module Prophet
  module Widgets
    class Radio
      attr_reader :name

      def initialize(name, value, state: 'normal')
        @name = name
        @variable = TkVariable.new
        @variable.value_type = value
        @state = state
      end

      def render(window)
        true_button = TkRadioButton.new(window).pack
        true_button.text = 'Yes'
        true_button.value = true
        true_button.variable = variable
        true_button.state = state

        false_button = TkRadioButton.new(window).pack
        false_button.text = 'No'
        false_button.value = false
        false_button.variable = variable
        false_button.state = state
      end

      def value
        variable.value
      end

      private

      attr_reader :variable, :state
    end
  end
end
