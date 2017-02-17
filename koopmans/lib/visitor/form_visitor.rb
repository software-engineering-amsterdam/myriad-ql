require_relative 'base_visitor'

class FormVisitor < BaseVisitor
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

  def visit_if_statement(subject)
    puts 'Visiting If Statement: %s' % subject.expression
  end
end