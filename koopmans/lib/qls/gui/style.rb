module QLS
  module GUI
    class Style
      attr_accessor :widget, :width, :font, :fontsize, :color

      def merge_with(style)
        @widget = widget || style.widget
        @width = width || style.width
        @font = font || style.font
        @fontsize = fontsize || style.fontsize
        @color = color || style.color
      end
    end
  end
end
