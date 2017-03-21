module QL
  module GUI
    class ComputedWidget < Widget
      def render(tk_frame)
        @variable = TkVariable.new

        entry = TkEntry.new(tk_frame).pack
        entry.textvariable = @variable
        entry.state = 'disabled'
      end

      def value=(value)
        @variable.value = value
      end
    end
  end
end
