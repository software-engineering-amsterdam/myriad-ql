module QL
  module GUI
    class TextWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = ''

        entry = TkEntry.new(tk_frame).pack
        entry.bind('KeyRelease') { callback(entry.value) }
      end
    end
  end
end