module QL
  module GUI
    class DropdownWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = true

        @true_label = 'true'
        @false_label = 'false'

        if options
          @true_label = options[:true_value]
          @false_label = options[:false_value]
        end

        combobox = Tk::Tile::Combobox.new(tk_frame).pack
        combobox.values = [@true_label, @false_label]
        combobox.value = @true_label
        combobox.bind('<ComboboxSelected>') { callback(to_value(combobox.value)) }

        callback(to_value(combobox.value))
      end

      def to_value(textual_value)
        if textual_value == @true_label
          true
        else
          false
        end
      end
    end
  end
end