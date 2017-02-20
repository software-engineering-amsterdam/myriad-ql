require_relative 'base_checker'

class DuplicateVariableChecker < BaseChecker
  # gather all variables from all questions and check for duplicates
  def visit_form(subject)
    variables = subject.statements.map { |statement| visit_statement(statement) }.flatten
    variables.select { |e| variables.count(e) > 1 }.uniq
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # only return the label of the given question
  def visit_question(subject)
    subject.variable.name
  end
end