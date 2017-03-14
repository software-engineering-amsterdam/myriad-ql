module QL
  module GUI
    class CheckboxWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = true

        variable = TkVariable.new(@default_value)

        check_button = TkCheckButton.new(tk_frame).pack
        check_button.variable = variable
        check_button.command = proc { callback(variable.bool) }

        callback(variable.bool)
      end
    end
  end
end