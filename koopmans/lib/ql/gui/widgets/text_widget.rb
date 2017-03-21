module QL
  module GUI
    class TextWidget < Widget
      def initialize(_options = nil)
        @default_value = ''
      end

      def render(tk_frame)
        entry = TkEntry.new(tk_frame).pack
        entry.bind('KeyRelease') { callback(entry.value) }
      end
    end
  end
end
