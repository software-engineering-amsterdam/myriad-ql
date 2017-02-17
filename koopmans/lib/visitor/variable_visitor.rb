require_relative '../visitor/base_visitor'

class VariableVisitor < BaseVisitor
  def visit_form(subject)
    @question_variables = subject.statements.map{|u| visit_statement(u)}.flatten
  end

  def visit_question(subject)
    subject.variable.name
  end
end