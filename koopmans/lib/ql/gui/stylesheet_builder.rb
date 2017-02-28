module QL
  module GUI
    class StylesheetBuilder
      attr_accessor :gui

      def initialize(qls_ast, ql_ast, gui)
        @gui = gui
        visit_stylesheet(qls_ast, ql_ast)
      end

      # gather all variables from all questions of the QL and from the QLS and check for reference errors
      def visit_stylesheet(stylesheet, form)
        qls_variables = stylesheet.pages.map { |page| page.accept(self) }.flatten.compact
        pp qls_variables
      end

      def visit_page(page)
        page.block.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.block.map { |element| element.accept(self) }
      end

      def visit_question(question)
        { question.variable.name => Array(question.properties).map { |property| property.accept(self) }.compact } if question.properties
      end

      def visit_default(default)
        # { default.type => Array(default.properties).map { |property| property.accept(self) }.compact } if default.properties
      end

      def visit_widget(widget)
        widget
      end

      def visit_property(property)
        property
      end
    end
  end
end