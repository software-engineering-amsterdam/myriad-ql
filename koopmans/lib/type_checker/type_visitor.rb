require_relative 'base_checker'

class TypeVisitor < BaseChecker
  # visit all statements in the form and return as hash
  def visit_form(subject)
    Hash[*subject.statements.map{|u| visit_statement(u)}.flatten]
  end

  # visit all statements in if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # only return variable name with its type for a question
  def visit_question(subject)
    [subject.variable.name, subject.type]
  end
end