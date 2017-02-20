require_relative 'base_checker'
require_relative 'variable_visitor'

class UndefinedVariableChecker < BaseChecker
  def visit_form(subject)
    # get all question variables
    # e.g. ["hasSoldHouse", "hasBoughtHouse", "hasMaintLoan"]
    @question_variables = subject.accept(VariableVisitor.new)

    # do the actual undefined variable checking
    subject.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  # visit calculation of the question
  def visit_question(subject)
    visit_calculation(subject.assignment) if subject.assignment
  end

  # visit calculation of if condition and visit all statements in if block
  def visit_if_statement(subject)
    test = []
    test.push(visit_calculation(subject.expression))
    test.push(subject.block.map { |statement| visit_statement(statement) })
  end

  # visit the calculations of both the left and right sides
  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  # only return the variable name if it is not existing
  def visit_variable(subject)
    unless @question_variables.include?(subject.name)
      "[ERROR]: variable '#{subject.name}' is undefined"
    end
  end
end