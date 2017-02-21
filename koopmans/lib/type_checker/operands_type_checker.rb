require_relative '../visitor/question_visitor'

class OperandsTypeChecker < BaseVisitor
  def visit_form(form)
    # get all variables and their types as defined by the questions
    # e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>, "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
    @types = form.accept(QuestionVisitor.new).map { |question| [question.variable.name, question.type] }.to_h

    # do the actual operands type checking
    form.statements.map { |statement| visit_statement(statement) }.flatten.compact
  end

  # visit calculation of the question
  def visit_question(question)
    visit_calculation(question.assignment) if question.assignment
  end

  # visit calculation of if condition and visit all statements in if block
  def visit_if_statement(if_statement)
    test = []
    test.push(visit_calculation(if_statement.expression))
    test.push(if_statement.block.map { |statement| visit_statement(statement) })
  end

  # variable is useless here
  def visit_variable(_)
  end

  def visit_negation(negation)
    expression_type = type(negation.expression)
    error(negation.class, expression_type) if ([type(negation).accept_types].flatten & [expression_type.accept_types].flatten).empty?
  end

  # an expression is checked for correctness
  def visit_expression(expression)
    subject_class = expression.class
    left_type = type(expression.left)
    right_type = type(expression.right)

    errors = []
    # the left side does not match the operator
    unless subject_class.includes_type?(left_type.accept_types)
      errors.push(error(left_type.accept_types.first, subject_class))
    end

    # the right side does not match the operator
    unless subject_class.includes_type?(right_type.accept_types)
      errors.push(error(right_type.accept_types.first, subject_class))
    end

    # the left and right side do not match
    if ([left_type.accept_types].flatten & [right_type.accept_types].flatten).empty?
      errors.push(error(left_type.accept_types.first, right_type))
    end

    errors.push([visit_calculation(expression.left), visit_calculation(expression.right)])
  end

  # get the type of a variable or other
  def type(left_or_right)
    if left_or_right.kind_of?(Variable)
      @types[left_or_right.name].class
    else
      left_or_right.class
    end
  end

  # generate error message
  def error(left, right)
    "[ERROR]: #{left} can not be used with #{right}"
  end
end