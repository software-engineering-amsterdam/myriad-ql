require_relative 'base_checker'

class CyclicVisitor < BaseChecker
  def visit_form(subject)
    # get question variables with associated assignment variables as hash
    subject.statements.map{|u| visit_statement(u)}.flatten.compact.inject(:merge)
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_question(subject)
    {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
  end

  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  def visit_variable(subject)
    subject
  end
end