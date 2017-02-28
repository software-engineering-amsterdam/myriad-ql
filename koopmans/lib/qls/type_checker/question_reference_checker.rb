module QLS
  module TypeChecker
    class QuestionReferenceChecker
      include QL::Visitor

      def visit_stylesheet(stylesheet, form)
        @ql_variables = form.accept(QL::Visitor::QuestionVisitor.new).map(&:variable).map(&:name)
        stylesheet.pages.map { |page| page.accept(self) }.flatten.compact
      end

      def visit_page(page)
        page.block.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.block.map { |element| element.accept(self) }
      end

      def visit_question(question)
        "[ERROR]: variable '#{question.variable.name}' is undefined" unless @ql_variables.include?(question.variable.name)
      end

      # default is useless here
      def visit_default(default)
      end
    end
  end
end