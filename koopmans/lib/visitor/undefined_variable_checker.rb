require_relative '../visitor/base_visitor'
require_relative 'variable_visitor'

class UndefinedVariableChecker < BaseVisitor
  def visit_form(subject)
    @question_variables = subject.accept(VariableVisitor.new)
    subject.statements.map{|u| visit_statement(u)}.flatten.compact
  end

  def visit_question(subject)
    visit_expression(subject.assignment) if subject.assignment
  end

  def visit_if_statement(subject)
    test = []
    test.push(visit_calculation(subject.expression))
    test.push(subject.block.map{|statement| visit_statement(statement)})
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
    nil
  end

  def visit_variable(subject)
    unless @question_variables.include?(subject.name)
      subject.name
    end
  end

  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end
end