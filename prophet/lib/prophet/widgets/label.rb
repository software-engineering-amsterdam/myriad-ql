module Prophet
  module Widgets
    class Label
      attr_reader :title

      def initialize(title)
        @title = title
      end

      def render(window)
        label = TkLabel.new(window)
        label.text = title
        label.pack
      end

      def value
        variable.value
      end
    end
  end
end
