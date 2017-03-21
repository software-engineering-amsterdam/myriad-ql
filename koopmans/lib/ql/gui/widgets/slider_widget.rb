module QL
  module GUI
    class SliderWidget < Widget
      def initialize(minimum = nil, maximum = nil)
        @default_value = 0
        @minimum = minimum || -100
        @maximum = maximum || 100
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
