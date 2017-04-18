module QLS
  module GUI
    class LabelWithStyle < SimpleDelegator
      attr_accessor :font, :color

      def initialize(label)
        @label = label
        super
      end

      def render(tk_frame)
        super
        tk_label.font = @font if @font
        tk_label.foreground = @color if @color
      end

      def apply_style(style)
        @font = create_tk_font(style.font, style.fontsize)
        @color = style.color
      end

      def create_tk_font(font, fontsize)
        tk_font_params = {}
        tk_font_params[:family] = font if font
        tk_font_params[:size] = fontsize if fontsize
        TkFont.new(tk_font_params)
      end
    end
  end
end
