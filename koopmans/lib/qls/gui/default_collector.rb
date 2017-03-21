module QLS
  module GUI
    class DefaultCollector
      def visit_stylesheet(stylesheet, _)
        @defaults = {}
        stylesheet.pages.map { |page| page.accept(self) }
        @defaults
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
        @defaults[parent_id] = QL::GUI::SliderWidget.new
      end

      def visit_spinbox_widget(spinbox_widget, parent_id)
        @defaults[parent_id] = QL::GUI::SpinboxWidget.new
      end

      def visit_text_widget(text_widget, parent_id)
        @defaults[parent_id] = QL::GUI::TextWidget.new
      end

      def visit_radio_widget(radio_widget, parent_id)
        options = {true_value: radio_widget.true_text.value, false_value: radio_widget.false_text.value}
        @defaults[parent_id] = QL::GUI::RadioWidget.new(options)
      end

      def visit_checkbox_widget(checkbox_widget, parent_id)
        @defaults[parent_id] = QL::GUI::CheckboxWidget.new
      end

      def visit_dropdown_widget(dropdown_widget, _)
        @defaults[parent_id] = QL::GUI::DropdownWidget.new
      end

      def visit_width(width, parent_id) end
      def visit_font(width, parent_id) end
      def visit_fontsize(width, parent_id) end
      def visit_color(width, parent_id) end
    end
  end
end
