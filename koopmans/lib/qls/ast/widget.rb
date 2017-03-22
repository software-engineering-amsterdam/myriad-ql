module QLS
  module AST
    class WidgetOptions
      attr_reader :first_value, :second_value

      def initialize(first_value, second_value)
        @first_value = first_value
        @second_value = second_value
      end

      # def accept(visitor)
      #   visitor.visit_widget_options(self)
      # end
    end

    class SliderWidget
      attr_reader :minimum, :maximum

      def initialize(minimum = nil, maximum = nil)
        @minimum = minimum
        @maximum = maximum
      end

      def accept(visitor, argument = nil)
        visitor.visit_slider_widget(self, argument)
      end

      def range
        return unless @minimum && @maximum
        [@minimum.value, @maximum.value]
      end
    end

    class SpinboxWidget
      attr_reader :options

      def initialize(options = nil)
        @options = options
      end

      def accept(visitor, argument = nil)
        visitor.visit_spinbox_widget(self, argument)
      end

      def range
        [@option.first_value.value, @option.second_value.value]
      end
    end

    class TextWidget
      def accept(visitor, argument = nil)
        visitor.visit_text_widget(self, argument)
      end
    end

    class RadioWidget
      attr_reader :true_label, :false_label

      def initialize(true_label = nil, false_label = nil)
        @true_label  = true_label
        @false_label = false_label
      end

      def accept(visitor, argument = nil)
        visitor.visit_radio_widget(self, argument)
      end

      def labels
        return unless @true_label && @false_label
        [@true_label.value, @false_label.value]
      end
    end

    class CheckboxWidget
      def accept(visitor, argument = nil)
        visitor.visit_checkbox_widget(self, argument)
      end
    end

    class DropdownWidget
      attr_reader :true_label, :false_label

      def initialize(true_label = nil, false_label = nil)
        @true_label  = true_label
        @false_label = false_label
      end

      def accept(visitor, argument = nil)
        visitor.visit_dropdown_widget(self, argument)
      end

      def labels
        return unless @true_label && @false_label
        [@true_label.value, @false_label.value]
      end
    end
  end
end
