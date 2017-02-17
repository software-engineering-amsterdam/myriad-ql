require_relative 'base_visitor'

class DuplicateVariableChecker < BaseVisitor
  def visit_form(subject)
    variables = subject.statements.map { |statement| visit_statement(statement) }.flatten
    variables.select { |e| variables.count(e) > 1 }.uniq
  end

  def visit_question(subject)
    subject.variable.name
  end
end