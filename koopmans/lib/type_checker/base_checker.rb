class BaseChecker
  def visit_statement(subject)
    if subject.kind_of?(Question)
      visit_question(subject)
    elsif subject.kind_of?(IfStatement)
      visit_if_statement(subject)
    else
      raise NotImplementedError
    end
  end

  def visit_calculation(subject)
    if subject.kind_of?(Literal)
      visit_literal(subject)
    elsif subject.kind_of?(Variable)
      visit_variable(subject)
    elsif subject.kind_of?(Expression)
      visit_expression(subject)
    else
      raise NotImplementedError
    end
  end

  def visit_literal(_)
  end
end