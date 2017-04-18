module QL
  module GUI
    class Label
      attr_reader :tk_label

      def initialize(text)
        @text = text
      end

      def render(tk_frame)
        @tk_label = TkLabel.new(tk_frame).pack
        @tk_label.text = @text
      end
    end
  end
end
