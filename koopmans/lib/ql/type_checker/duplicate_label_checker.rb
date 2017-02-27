module QL
  module TypeChecker
    class DuplicateLabelChecker
      include Visitor

      # gather all labels from all questions and check for duplicates
      def visit_form(form)
        labels = form.accept(QuestionVisitor.new).map(&:label)
        labels = labels.select { |label| labels.count(label) > 1 }.uniq
        labels.map { |label| "[WARNING]: question with label '#{label}' is defined multiple times" }
      end
    end
  end
end