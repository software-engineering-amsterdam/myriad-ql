module QL
  module GUI
    class SliderWidget < Widget
      def initialize(minimum=-100, maximum=100)
        @default_value = 0
        @minimum = minimum
        @maximum = maximum
      end

      def render(tk_frame)
        scale = TkScale.new(tk_frame).pack
        scale.from = @minimum
        scale.to = @maximum
        scale.command = proc { callback(scale.value) }
      end
    end
  end
end
