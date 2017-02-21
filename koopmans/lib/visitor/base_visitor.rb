class BaseVisitor
  # is it a question or an if statement?
  def visit_statement(statement)
    if statement.kind_of?(Question)
      visit_question(statement)
    elsif statement.kind_of?(IfStatement)
      visit_if_statement(statement)
    else
      raise NotImplementedError
    end
  end

  # is the calculation a literal, a variable or an expression?
  def visit_calculation(calculation)
    if calculation.kind_of?(Literal)
      visit_literal(calculation)
    elsif calculation.kind_of?(Variable)
      visit_variable(calculation)
    elsif calculation.kind_of?(BinaryExpression)
      visit_expression(calculation)
    elsif calculation.kind_of?(Negation)
      visit_negation(calculation)
    else
      raise NotImplementedError
    end
  end

  # nothing has to be done with a literal
  def visit_literal(_)
  end
end