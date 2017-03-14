module QL
  module GUI
    class ComputedWidget < Widget
      def initialize(tk_frame, options=nil)
        @variable = TkVariable.new

        entry = TkEntry.new(tk_frame).pack
        entry.textvariable = @variable
        entry.state = 'disabled'
      end

      def set_value(value)
        @variable.value = value
      end
    end
  end
end