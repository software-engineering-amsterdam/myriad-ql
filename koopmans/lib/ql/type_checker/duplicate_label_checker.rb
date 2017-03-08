module QL
  module TypeChecker
    class DuplicateLabelChecker
      include Visitor
      include Notification

      # gather all labels from all questions and check for duplicates
      def visit_form(form)
        # labels = form.accept(BaseCollector.new).flatten.count
        pp labels
        # labels = labels.select { |label| labels.count(label) > 1 }.uniq
        # labels.map { |label| Notification.new("question with label '#{label}' is defined multiple times") }
      end
    end
  end
end