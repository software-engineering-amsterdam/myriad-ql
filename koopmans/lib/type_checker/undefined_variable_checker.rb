require_relative '../visitor/question_visitor'

class UndefinedVariableChecker
  def visit_form(form)
    # get all question variables
    # e.g. ["hasSoldHouse", "hasBoughtHouse", "hasMaintLoan"]
    @question_variables = form.accept(QuestionVisitor.new).map(&:variable).map(&:name)

    # do the actual undefined variable checking
    form.statements.map { |statement| statement.accept(self) }.flatten.compact
  end

  # visit calculation of the question
  def visit_question(question)
    question.assignment.accept(self) if question.assignment
  end

  # visit calculation of if condition and visit all statements in if block
  def visit_if_statement(if_statement)
    test = []
    test.push(if_statement.expression.accept(self))
    test.push(if_statement.block.map { |statement| statement.accept(self) })
  end

  # visit the calculation of the negation expression
  def visit_negation(negation)
    negation.expression.accept(self)
  end

  # visit the calculations of both the left and right sides
  def visit_expression(expression)
    [expression.left.accept(self), expression.right.accept(self)]
  end

  # nothing has to be done with a literal
  def visit_literal(_)
  end

  # only return the variable name if it is not existing
  def visit_variable(variable)
    unless @question_variables.include?(variable.name)
      "[ERROR]: variable '#{variable.name}' is undefined"
    end
  end
end