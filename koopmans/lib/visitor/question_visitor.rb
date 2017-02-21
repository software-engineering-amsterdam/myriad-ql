class QuestionVisitor
  # gather all labels from all questions and check for duplicates
  def visit_form(form)
    @questions = form.statements.map { |statement| statement.accept(self) }.flatten
  end

  # visit all statements of the if block
  def visit_if_statement(if_statement)
    if_statement.block.map { |statement| statement.accept(self) }
  end

  # return question
  def visit_question(question)
    question
  end

  # visit the calculations of both the left and right sides
  def visit_expression(expression)
    [expression.left.accept(self), expression.right.accept(self)]
  end

  # return the variable
  def visit_variable(variable)
    [variable]
  end

  # nothing has to be done with a literal
  def visit_literal(_)
  end

  # visit the calculation of the negation expression
  def visit_negation(negation)
    negation.expression.accept(self)
  end
end