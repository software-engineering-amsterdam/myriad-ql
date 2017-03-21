module QL
  module GUI
    class SpinboxWidget < Widget
      def initialize(minimum=-100, maximum=100)
        @default_value = 0
        @minimum = minimum
        @maximum = maximum
      end

      def render(tk_frame)
        spinbox = TkSpinbox.new(tk_frame).pack
        spinbox.from = @minimum
        spinbox.to = @maximum
        spinbox.value = @default_value
        spinbox.command = proc { callback(spinbox.value) }
      end
    end
  end
end
