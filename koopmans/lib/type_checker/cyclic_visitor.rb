require_relative 'base_checker'

class CyclicVisitor < BaseChecker
  # visit all statements in the form
  def visit_form(subject)
    subject.statements.map { |u| visit_statement(u) }.flatten.compact.inject(:merge)
  end

  # visit all statements in if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # visit question, and visit calculation for the assignment of the question
  def visit_question(subject)
    {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
  end

  # visit the calculations of both the left and right sides
  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  # return the variable
  def visit_variable(subject)
    [subject]
  end
end