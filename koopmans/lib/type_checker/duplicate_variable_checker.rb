require_relative 'base_checker'

class DuplicateVariableChecker < BaseChecker
  # gather all variables from all questions and check for duplicates
  def visit_form(subject)
    variables = subject.statements.map { |statement| visit_statement(statement) }.flatten.map(&:variable)
    variables = variables.select { |e| variables.count(e) > 1 }.uniq
    variables.map{|variable| "[ERROR]: variable '#{variable.name}' is defined multiple times"}
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # return question
  def visit_question(subject)
    subject
  end
end