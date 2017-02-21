require_relative '../visitor/question_visitor'

class UndefinedVariableChecker < BaseVisitor
  def visit_form(form)
    # get all question variables
    # e.g. ["hasSoldHouse", "hasBoughtHouse", "hasMaintLoan"]
    @question_variables = form.accept(QuestionVisitor.new).map(&:variable).map(&:name)

    # do the actual undefined variable checking
    form.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  # visit calculation of the question
  def visit_question(question)
    visit_calculation(question.assignment) if question.assignment
  end

  # visit calculation of if condition and visit all statements in if block
  def visit_if_statement(if_statement)
    test = []
    test.push(visit_calculation(if_statement.expression))
    test.push(if_statement.block.map { |statement| visit_statement(statement) })
  end

  # visit the calculation of the negation expression
  def visit_negation(negation)
    visit_calculation(negation.expression)
  end

  # visit the calculations of both the left and right sides
  def visit_expression(expression)
    [visit_calculation(expression.left), visit_calculation(expression.right)]
  end

  # only return the variable name if it is not existing
  def visit_variable(variable)
    unless @question_variables.include?(variable.name)
      "[ERROR]: variable '#{variable.name}' is undefined"
    end
  end
end