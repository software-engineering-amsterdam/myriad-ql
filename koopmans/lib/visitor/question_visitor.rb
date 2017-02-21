require_relative 'base_visitor'

class QuestionVisitor < BaseVisitor
  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    @questions = subject.statements.map { |statement| visit_statement(statement) }.flatten
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # return question
  def visit_question(subject)
    subject
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