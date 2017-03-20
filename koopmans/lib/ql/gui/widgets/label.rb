module QL
  module GUI
    class Label
      def initialize(tk_frame, label)
        tk_label = TkLabel.new(tk_frame).pack
        tk_label.text = label
      end
    end
  end
end
