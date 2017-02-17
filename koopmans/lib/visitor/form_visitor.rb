require_relative 'base_visitor'

class FormVisitor < BaseVisitor
  def visit_Question(subject)
    puts 'Visiting Question: %s' % subject.label
  end

  def visit_Variable(subject)
    puts 'Visiting Variable: %s' % subject.name
  end

  def visit_IfStatement(subject)
    puts 'Visiting If Statement: %s' % subject.expression
  end
end