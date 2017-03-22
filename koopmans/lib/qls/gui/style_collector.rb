module QLS
  module GUI
    class StyleCollector
      attr_reader :styles

      def initialize
        @styles = {}
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        @styles[page.object_id] = Style.new
        page.body.map { |element| element.accept(self, page.object_id) }
      end

      def visit_section(section, _)
        @styles[section.object_id] = Style.new
        section.body.map { |element| element.accept(self, section.object_id) }
      end

      def visit_question(question, _)
        @styles[question.object_id] = Style.new
        question.properties.map { |element| element.accept(self, question.object_id) }
      end

      def visit_default_properties(default, parent_id)
        default.properties.map { |property| property.accept(self, parent_id) }
      end

      def visit_slider_widget(slider_widget, parent_id)
        minimum, maximum = try_visit_widget_options(slider_widget)
        @styles[parent_id].widget = QL::GUI::SliderWidget.new(minimum, maximum)
      end

      def visit_spinbox_widget(spinbox_widget, parent_id)
        minimum, maximum = try_visit_widget_options(spinbox_widget)
        @styles[parent_id].widget = QL::GUI::SpinboxWidget.new(minimum, maximum)
      end

      def visit_text_widget(_, parent_id)
        @styles[parent_id].widget = QL::GUI::TextWidget.new
      end

      def visit_radio_widget(radio_widget, parent_id)
        true_label, false_label = try_visit_widget_options(radio_widget)
        @styles[parent_id].widget = QL::GUI::RadioWidget.new(true_label, false_label)
      end

      def visit_checkbox_widget(_, parent_id)
        @styles[parent_id].widget = QL::GUI::CheckboxWidget.new
      end

      def visit_dropdown_widget(dropdown_widget, _)
        true_label, false_label = try_visit_widget_options(dropdown_widget)
        @styles[parent_id].widget = QL::GUI::DropdownWidget.new(true_label, false_label)
      end

      def visit_widget_options(widget_options)
        return unless widget_options.first_value && widget_options.second_value
        [widget_options.first_value.value, widget_options.second_value.value]
      end

      def visit_width(width, parent_id)
        @styles[parent_id].width = width.value.value
      end

      def visit_font(width, parent_id) end

      def visit_fontsize(width, parent_id) end

      def visit_color(width, parent_id) end

      def try_visit_widget_options(widget)
        return unless widget.widget_options
        widget.widget_options.accept(self)
      end
    end
  end
end
