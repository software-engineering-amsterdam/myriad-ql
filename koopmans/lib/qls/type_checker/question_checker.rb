module QLS
  module TypeChecker
    class QuestionChecker
      include Notification

      # gather all variables from all questions of the QL and from the QLS and check for reference errors
      def visit_stylesheet(stylesheet, form)
        ql_variables            = form.accept(QuestionVisitor.new).map(&:variable).map(&:name)
        qls_variables           = stylesheet.pages.map { |page| page.accept(self) }.flatten.compact
        duplicate_qls_variables = qls_variables.select { |variable| qls_variables.count(variable) > 1 }.uniq

        errors = []
        errors.push((ql_variables - qls_variables).map { |error| Notification.new("#{error} of the QL program is not placed by the QLS program.") })
        errors.push((qls_variables - ql_variables).map { |error| Notification.new("#{error} is referenced to a question that is not in the QL program") })
        errors.push((duplicate_qls_variables).map { |error| Notification.new("#{error} is placed multiple times") })
        errors.flatten.uniq
      end

      def visit_page(page)
        page.block.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.block.map { |element| element.accept(self) }
      end

      def visit_question(question)
        question.variable.name
      end

      # default is useless here
      def visit_default(_)
      end
    end
  end
end