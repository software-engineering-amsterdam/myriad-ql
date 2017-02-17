require_relative 'base_checker'

class TypeVisitor < BaseChecker
  def visit_form(subject)
    # get question variables with associated type
    Hash[*subject.statements.map{|u| visit_statement(u)}.flatten]
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_question(subject)
    [subject.variable.name, subject.type]
  end
end