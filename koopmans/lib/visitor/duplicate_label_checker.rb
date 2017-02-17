class DuplicateLabelChecker
  def visit_form(subject)
    labels = subject.statements.map{|u| visit_statement(u)}.flatten.map(&:label)
    labels.detect{ |e| labels.count(e) > 1 }
  end

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
    subject.block.each do |statement|
      visit_statement(statement)
    end
  end

  def visit_question(subject)
    subject
  end
end