module QLS
  module AST
    class SliderWidget
      attr_reader :minimum, :maximum

      def initialize(minimum, maximum)
        @minimum = minimum
        @maximum = maximum
      end

      def accept(visitor, type = nil)
        visitor.visit_slider_widget(self, type)
      end
    end

    class SpinboxWidget
      def accept(visitor, type = nil)
        visitor.visit_spinbox_widget(self, type)
      end
    end

    class TextWidget
      def accept(visitor, type = nil)
        visitor.visit_text_widget(self, type)
      end
    end

    class RadioWidget
      attr_reader :true_text, :false_text

      def initialize(true_text, false_text)
        @true_text  = true_text
        @false_text = false_text
      end

      def accept(visitor, type = nil)
        visitor.visit_radio_widget(self, type)
      end
    end

    class CheckboxWidget
      def accept(visitor, type = nil)
        visitor.visit_checkbox_widget(self, type)
      end
    end

    class DropdownWidget
      attr_reader :true_text, :false_text

      def initialize(true_text, false_text)
        @true_text  = true_text
        @false_text = false_text
      end

      def accept(visitor, type = nil)
        visitor.visit_dropdown_widget(self, type)
      end
    end
  end
end
