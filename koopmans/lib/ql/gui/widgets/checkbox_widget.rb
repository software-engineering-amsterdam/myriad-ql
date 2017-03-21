module QL
  module GUI
    class CheckboxWidget < Widget
      def initialize
        @default_value = false
      end

      def render(tk_frame)
        variable = TkVariable.new(@default_value)

        check_button = TkCheckButton.new(tk_frame).pack
        check_button.variable = variable
        check_button.command = proc { callback(variable.bool) }
      end
    end
  end
end
