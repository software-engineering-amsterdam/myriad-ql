require_relative '../visitor/question_visitor'
module QL
  module TypeChecker
    class DuplicateVariableChecker
      # gather all variables from all questions and check for duplicates
      def visit_form(form)
        variables = form.accept(QuestionVisitor.new).map(&:variable).map(&:name)
        variables = variables.select { |e| variables.count(e) > 1 }.uniq
        variables.map { |variable| "[ERROR]: variable '#{variable}' is defined multiple times" }
      end
    end
  end
end