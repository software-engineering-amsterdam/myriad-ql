module QLS
  module AST
    class SliderWidget
      attr_reader :minimum, :maximum

      def initialize(minimum = nil, maximum = nil)
        @minimum = minimum
        @maximum = maximum
      end

      def accept(visitor, type = nil)
        visitor.visit_slider_widget(self, type)
      end

      def range
        return unless @minimum && @maximum
        [@minimum.value, @maximum.value]
      end
    end

    class SpinboxWidget
      attr_reader :minimum, :maximum

      def initialize(minimum = nil, maximum = nil)
        @minimum = minimum
        @maximum = maximum
      end

      def accept(visitor, type = nil)
        visitor.visit_spinbox_widget(self, type)
      end

      def range
        return unless @minimum && @maximum
        [@minimum.value, @maximum.value]
      end
    end

    class TextWidget
      def accept(visitor, type = nil)
        visitor.visit_text_widget(self, type)
      end
    end

    class RadioWidget
      attr_reader :true_label, :false_label

      def initialize(true_label = nil, false_label = nil)
        @true_label  = true_label
        @false_label = false_label
      end

      def accept(visitor, type = nil)
        visitor.visit_radio_widget(self, type)
      end

      def labels
        return unless @true_label && @false_label
        [@true_label.value, @false_label.value]
      end
    end

    class CheckboxWidget
      def accept(visitor, type = nil)
        visitor.visit_checkbox_widget(self, type)
      end
    end

    class DropdownWidget
      attr_reader :true_label, :false_label

      def initialize(true_label = nil, false_label = nil)
        @true_label  = true_label
        @false_label = false_label
      end

      def accept(visitor, type = nil)
        visitor.visit_dropdown_widget(self, type)
      end

      def labels
        return unless @true_label && @false_label
        [@true_label.value, @false_label.value]
      end
    end
  end
end
