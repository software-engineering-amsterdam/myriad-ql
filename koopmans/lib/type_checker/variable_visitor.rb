require_relative 'base_checker'

class VariableVisitor < BaseChecker
  def visit_form(subject)
    subject.statements.map{|u| visit_statement(u)}.flatten
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_question(subject)
    subject.variable.name
  end
end