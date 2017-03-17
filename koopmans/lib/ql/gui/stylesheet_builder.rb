# TODO use wrapper pattern to apply styles
module QL
  module GUI
    class StylesheetBuilder
      attr_accessor :gui

      def initialize(qls_ast, ql_ast, gui)
        @gui = gui
        visit_stylesheet(qls_ast, ql_ast)
      end

      # gather all widgets
      def visit_stylesheet(stylesheet, form)
        widgets = stylesheet.pages.map { |page| page.accept(self) }.flatten.compact
        # p '------------'
        # pp widgets
      end

      def visit_page(page)
        page.block.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.block.map { |element| element.accept(self) }
      end

      def visit_question(question)
        { question.variable.name => question.properties } if question.properties
      end

      def visit_default(default)
        # p default
        # { default.type => Array(default.properties).map { |property| property.accept(self) }.compact } if default.properties
      end

      def visit_widget(widget)
        # widget
      end

      def visit_property(property)
        # property
      end
    end
  end
end