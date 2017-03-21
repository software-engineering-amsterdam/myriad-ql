module QLS
  module GUI
    class DefaultCollector
      attr_reader :defaults

      def initialize
        @defaults = {}
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.map { |element| element.accept(self, page.object_id) }
      end

      def visit_section(section, _)
        section.body.map { |element| element.accept(self, section.object_id) }
      end

      def visit_question(question, _)
        question.properties.map { |element| element.accept(self, question.object_id) }
      end

      def visit_default(default, parent_id)
        default.properties.map { |property| property.accept(self, parent_id) }
      end

      def visit_slider_widget(slider_widget, parent_id)
        minimum, maximum = slider_widget.range
        @defaults[parent_id] = QL::GUI::SliderWidget.new(minimum, maximum)
      end

      def visit_spinbox_widget(spinbox_widget, parent_id)
        minimum, maximum = spinbox_widget.range
        @defaults[parent_id] = QL::GUI::SpinboxWidget.new(minimum, maximum)
      end

      def visit_text_widget(_, parent_id)
        @defaults[parent_id] = QL::GUI::TextWidget.new
      end

      def visit_radio_widget(radio_widget, parent_id)
        true_label, false_label = radio_widget.labels
        @defaults[parent_id] = QL::GUI::RadioWidget.new(true_label, false_label)
      end

      def visit_checkbox_widget(_, parent_id)
        @defaults[parent_id] = QL::GUI::CheckboxWidget.new
      end

      def visit_dropdown_widget(dropdown_widget, _)
        true_label, false_label = dropdown_widget.labels
        @defaults[parent_id] = QL::GUI::DropdownWidget.new(true_label, false_label)
      end

      def visit_width(width, parent_id) end
      def visit_font(width, parent_id) end
      def visit_fontsize(width, parent_id) end
      def visit_color(width, parent_id) end
    end
  end
end
