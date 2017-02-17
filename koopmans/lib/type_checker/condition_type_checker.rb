require_relative 'base_checker'
require_relative 'type_visitor'

class ConditionTypeChecker < BaseChecker
  def visit_form(subject)
    @types = subject.accept(TypeVisitor.new)
    subject.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  def visit_question(_)
  end

  def visit_if_statement(subject)
    test = []
    test.push(visit_calculation(subject.expression))
    test.push(subject.block.map { |statement| visit_statement(statement) })
  end

  def visit_variable(subject)
    unless @types[subject.name].kind_of?(BooleanType)
      subject.name
    end
  end

  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end
end