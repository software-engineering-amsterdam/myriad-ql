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

      def visit_question(question)
        question.properties.map { |property| property.accept(self) }
      end

      def visit_default(default)
        # { default.type => default.properties[:widget] } if default.properties
      end

      def visit_widget(widget)
        widget
      end

      def visit_property(_)
      end
    end
  end
end