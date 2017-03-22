module QLS
  module AST
    class WidgetOptions
      attr_reader :first_value, :second_value

      def initialize(first_value, second_value)
        @first_value = first_value
        @second_value = second_value
      end

      def accept(visitor)
        visitor.visit_widget_options(self)
      end
    end

    class Widget
      attr_reader :widget_options

      def initialize(widget_options = nil)
        @widget_options = widget_options
      end
    end

    class SliderWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_slider_widget(self, argument)
      end
    end

    class SpinboxWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_spinbox_widget(self, argument)
      end
    end

    class TextWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_text_widget(self, argument)
      end
    end

    class RadioWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_radio_widget(self, argument)
      end
    end

    class CheckboxWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_checkbox_widget(self, argument)
      end
    end

    class DropdownWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_dropdown_widget(self, argument)
      end
    end
  end
end
