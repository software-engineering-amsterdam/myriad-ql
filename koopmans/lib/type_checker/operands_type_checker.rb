require_relative '../visitor/question_visitor'

class OperandsTypeChecker < BaseVisitor
  def visit_form(subject)
    # get all variables and their types as defined by the questions
    # e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>, "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
    @types = subject.accept(QuestionVisitor.new).map { |question| [question.variable.name, question.type]}.to_h

    # do the actual operands type checking
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

  # variable is useless here
  def visit_variable(_)
  end

  # an expression is checked for correctness
  def visit_expression(subject)
    subject_class = subject.class
    left_type = type(subject.left)
    right_type = type(subject.right)

    errors = []
    # the left side does not match the operator
    unless subject_class.includes_type?(left_type)
      errors.push(error(left_type, subject_class.to_operator))
    end

    # the right side does not match the operator
    unless subject_class.includes_type?(right_type)
      errors.push(error(right_type, subject_class.to_operator))
    end

    # the left and right side do not match
    if ([left_type].flatten & [right_type].flatten).empty?
      errors.push(error(left_type, right_type))
    end

    errors.push([visit_calculation(subject.left), visit_calculation(subject.right)])
  end

  # get the type of a variable or other
  def type(left_or_right)
    if left_or_right.kind_of?(Variable)
      @types[left_or_right.name].class
    else
      left_or_right.class.real_type
    end
  end

  # generate error message
  def error(left, right)
    "[ERROR]: #{left} can not be used with #{right}"
  end
end