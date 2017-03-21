module QL
  module GUI
    class SpinboxWidget < Widget
      def initialize(minimum = nil, maximum = nil)
        @default_value = 0
        @minimum = minimum || -100
        @maximum = maximum || 100
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
