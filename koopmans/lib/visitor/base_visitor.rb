class BaseVisitor
  def visit_statement(subject)
    if subject.kind_of?(Question)
      visit_question(subject)
    elsif subject.kind_of?(IfStatement)
      visit_if_statement(subject)
    else
      raise NotImplementedError
    end
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end
end