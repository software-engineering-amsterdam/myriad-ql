require_relative 'base_checker'
require_relative 'type_visitor'

# TODO literal true and false should also be allowed:
# if(true && false)

class ConditionTypeChecker < BaseChecker
  def visit_form(subject)
    # get all variables and their types as defined by the questions
    # e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>, "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
    @types = subject.accept(TypeVisitor.new)
    # do the actual condition type checking
    subject.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  # question is useless here
  def visit_question(_)
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

  # only return the variable name if it is not of a boolean type
  def visit_variable(subject)
    unless @types[subject.name].kind_of?(BooleanType)
      "[ERROR]: variable '#{subject.name}' is supposed to by a Boolean"
    end
  end

  # only return the literal value if it is not of a boolean type
  def visit_literal(subject)
    unless subject.class.real_type == BooleanType
      "[ERROR]: '#{subject.value}' is supposed to by a Boolean"
    end
  end
end