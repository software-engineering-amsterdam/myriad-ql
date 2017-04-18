module QLS
  module AST
    class Widget
      attr_reader :widget_options

      def initialize(widget_options = nil)
        @widget_options = widget_options
      end
    end
  end
end
