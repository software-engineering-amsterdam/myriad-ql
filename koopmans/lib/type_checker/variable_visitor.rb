require_relative 'base_checker'

class VariableVisitor < BaseChecker
  def visit_form(subject)
    subject.statements.map{|u| visit_statement(u)}.flatten
  end

  def visit_question(subject)
    subject.variable.name
  end
end