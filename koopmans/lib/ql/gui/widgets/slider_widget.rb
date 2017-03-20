module QL
  module GUI
    class SliderWidget < Widget
      def initialize(tk_frame, options = nil)
        @default_value = 0

        @minimum = 0
        @maximum = 100

        if options
          @minimum = options[:minimum]
          @maximum = options[:maximum]
        end

        scale = TkScale.new(tk_frame).pack
        scale.from = @minimum
        scale.to = @maximum
        scale.command = proc { callback(scale.value) }
      end
    end
  end
end
