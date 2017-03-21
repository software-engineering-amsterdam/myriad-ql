module QLS
  module TypeChecker
    class QuestionVariableCollector
      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.body.map { |element| element.accept(self) }
      end

      def visit_question(question)
        question.variable.accept(self)
      end

      def visit_variable(variable)
        variable
      end

      def visit_default(_) end
    end
  end
end
