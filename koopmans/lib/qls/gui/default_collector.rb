module QLS
  module GUI
    class DefaultCollector
      def visit_stylesheet(stylesheet)
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

      def visit_question(_, _) end

      def visit_default(default, parent_id)
        @defaults[parent_id] = default
      end
    end
  end
end
