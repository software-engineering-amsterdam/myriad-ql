class FormVisitor
  def visit_statement(subject)
    if subject.kind_of?(Question)
      visit_question(subject)
    elsif subject.kind_of?(IfStatement)
      visit_if_statement(subject)
    else
      raise NotImplementedError
    end
  end

  def visit_question(subject)
    p "Label: #{subject.label}"
    visit_variable(subject.variable)
    visit_type(subject.type)
    visit_calculation(subject.assignment) if subject.assignment
    p ''
  end

  def visit_variable(subject)
    puts 'Visiting Variable: %s' % subject.name
  end

  def visit_type(subject)
    puts 'Visiting Type: %s' % subject.class.type
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

  def visit_literal(subject)
    p subject.value
  end

  def visit_variable(subject)
    p subject.name
  end

  def visit_expression(subject)
    visit_calculation(subject.left)
    visit_calculation(subject.right)
  end

  def visit_if_statement(subject)
    puts 'Visiting If Statement: %s' % subject.expression
    visit_calculation(subject.expression)
    p ''
    subject.block.each do |statement|
      visit_statement(statement)
    end
  end
end