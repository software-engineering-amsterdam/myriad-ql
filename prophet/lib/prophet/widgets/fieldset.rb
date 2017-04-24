module Prophet
  module Widgets
    class Fieldset
      attr_reader :name, :label, :input

      def initialize(name, label, input)
        @name = name
        @label = label
        @input = input
      end

      def render(window)
        frame = TkFrame.new(window).pack
        label.render(frame)
        input.render(frame)
      end

      def value
        input.value
      end
    end
  end
end
