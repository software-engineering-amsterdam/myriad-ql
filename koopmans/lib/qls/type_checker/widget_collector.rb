module QLS
  module TypeChecker
    class WidgetCollector
      def visit_stylesheet(stylesheet, collected_data)
        @variable_type_map = collected_data
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.body.map { |element| element.accept(self) }
      end

      #TODO global var?
      def visit_question(question)
        widget = question.properties.map { |property| property.accept(self) }.compact
        return if widget.empty?
        { @variable_type_map[question.variable.name] => widget }
      end

      #TODO global var?
      def visit_default(default)
        widget = default.properties.map { |property| property.accept(self) }.compact
        return if widget.empty?
        { default.type => widget }
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

      def visit_property(_) end
    end
  end
end