module QL
  module GUI
    class DropdownWidget < Widget
      def initialize(true_label='True', false_label='False')
        @default_value = false
        @true_label = true_label
        @false_label = false_label
      end

      def render(tk_frame)
        combobox = Tk::Tile::Combobox.new(tk_frame).pack
        combobox.values = [@true_label, @false_label]
        combobox.value = @false_label
        combobox.bind('<ComboboxSelected>') { callback(to_value(combobox.value)) }
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
