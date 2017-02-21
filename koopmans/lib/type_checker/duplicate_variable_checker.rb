require_relative '../visitor/question_visitor'

class DuplicateVariableChecker < BaseVisitor
  # gather all variables from all questions and check for duplicates
  def visit_form(subject)
    variables = subject.accept(QuestionVisitor.new).map(&:variable)
    variables = variables.select { |e| variables.count(e) > 1 }.uniq
    variables.map{|variable| "[ERROR]: variable '#{variable.name}' is defined multiple times"}
  end
end