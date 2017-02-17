class FormVisitor
  def visit_statement(subject)
    if subject.kind_of?(Question)
      visit_question(subject)
    elsif subject.kind_of?(IfStatement)
      visit_if_statement(subject)
    end
  end

  def visit_question(subject)
    puts 'Visiting Question: %s' % subject

    p "Label: #{subject.label}"
    visit_variable(subject.variable)
    visit_type(subject.type)
    visit_assignment(subject.assignment) if subject.assignment
    p ''
  end

  def visit_variable(subject)
    puts 'Visiting Variable: %s' % subject.name
  end

  def visit_type(subject)
    puts 'Visiting Type: %s' % subject.class.type
  end

  def visit_assignment(subject)
    # TODO
    puts 'Visiting Assignment'
  end

  def visit_if_statement(subject)
    puts 'Visiting If Statement: %s' % subject.expression
    p ''
    subject.block.each do |statement|
      visit_statement(statement)
    end
  end
end