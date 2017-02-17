require_relative 'base_checker'

class DuplicateVariableChecker < BaseChecker
  def visit_form(subject)
    variables = subject.statements.map { |statement| visit_statement(statement) }.flatten
    variables.select { |e| variables.count(e) > 1 }.uniq
  end

  def visit_question(subject)
    subject.variable.name
  end
end