require_relative 'base_visitor'

class QuestionVisitor < BaseVisitor
  # gather all labels from all questions and check for duplicates
  def visit_form(form)
    @questions = form.statements.map { |statement| visit_statement(statement) }.flatten
  end

  # visit all statements of the if block
  def visit_if_statement(if_statement)
    if_statement.block.map { |statement| visit_statement(statement) }
  end

  # return question
  def visit_question(question)
    question
  end

  # visit the calculations of both the left and right sides
  def visit_expression(expression)
    [visit_calculation(expression.left), visit_calculation(expression.right)]
  end

  # return the variable
  def visit_variable(variable)
    [variable]
  end

  # visit the calculation of the negation expression
  def visit_negation(negation)
    visit_calculation(negation.expression)
  end
end