module QLS
  module TypeChecker
    class TypeWidgetMapCollector
      def visit_stylesheet(stylesheet, collected_data)
        @variable_type_map = collected_data
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.map { |element| element.accept(self) }
      end

      def visit_section(section, _)
        section.body.map { |element| element.accept(self) }
      end

      def visit_question(question, _)
        question_type = @variable_type_map[question.variable.name]
        question.properties.map { |property| property.accept(self, question_type) }
      end

      def visit_default(default, _)
        default.properties.map { |property| property.accept(self, default.type) }
      end

      def visit_slider_widget(slider_widget, _)
        slider_widget
      end

      def visit_spinbox_widget(spinbox_widget, _)
        spinbox_widget
      end

      def visit_text_widget(text_widget, _)
        text_widget
      end

      def visit_radio_widget(radio_widget, _)
        radio_widget
      end

      def visit_checkbox_widget(checkbox_widget, _)
        checkbox_widget
      end

      def visit_dropdown_widget(dropdown_widget, _)
        dropdown_widget
      end

      def visit_property(_, _) end
    end
  end
end