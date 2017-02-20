require_relative 'base_checker'

class VariableVisitor < BaseChecker
  # visit all statements in the form
  def visit_form(subject)
    subject.statements.map{|u| visit_statement(u)}.flatten
  end

  # visit all statements in if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # only return variable name for a question
  def visit_question(subject)
    subject.variable.name
  end
end