require_relative 'base_checker'
require_relative 'variable_visitor'

class UndefinedVariableChecker < BaseChecker
  def visit_form(subject)
    @question_variables = subject.accept(VariableVisitor.new)
    subject.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  def visit_question(subject)
    visit_expression(subject.assignment) if subject.assignment
  end

  def visit_if_statement(subject)
    test = []
    test.push(visit_calculation(subject.expression))
    test.push(subject.block.map { |statement| visit_statement(statement) })
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