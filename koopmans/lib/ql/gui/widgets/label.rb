module QL
  module GUI
    class Label
      def initialize(label)
        @label = label
      end

      def render(tk_frame)
        tk_label = TkLabel.new(tk_frame).pack
        tk_label.text = @label
      end
    end
  end
end
