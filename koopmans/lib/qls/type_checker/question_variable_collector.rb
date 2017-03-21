module QLS
  module TypeChecker
    class QuestionVariableCollector
      attr_reader :variables

      def initialize
        @variables = []
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.each { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.each { |element| element.accept(self) }
      end

      def visit_section(section, _)
        section.body.each { |element| element.accept(self) }
      end

      def visit_question(question, _)
        question.variable.accept(self)
      end

      def visit_variable(variable)
        @variables << variable
      end

      def visit_default(_, _) end
    end
  end
end
