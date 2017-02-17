require_relative 'base_checker'
require_relative 'type_visitor'

class OperandsTypeChecker < BaseChecker
  def visit_form(subject)
    @types = subject.accept(TypeVisitor.new)
    # TODO fix errors array
    @errors = []
    subject.statements.map { |statement| visit_statement(statement) }
    @errors.flatten.compact
  end

  def visit_question(subject)
    visit_expression(subject.assignment) if subject.assignment
  end

  def visit_if_statement(subject)
    @errors.push(visit_calculation(subject.expression))
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_variable(_)
  end

  def visit_expression(subject)
    subject_type = subject.class.real_type

    if subject.left.kind_of?(Variable)
      left_type = @types[subject.left.name].class
    else
      left_type = subject.left.class.real_type
    end

    if subject.right.kind_of?(Variable)
      right_type = @types[subject.right.name].class
    else
      right_type = subject.right.class.real_type
    end

    if subject_type != left_type
      @errors.push("[ERROR]: #{subject.left.name} of type #{left_type} can not be used with #{subject.class.to_operator} operator")
    end

    if subject_type != right_type
      @errors.push("[ERROR]: #{subject.right.name} of type #{right_type} can not be used with #{subject.class.to_operator} operator")
    end

    if left_type != right_type
      @errors.push("[ERROR]: #{subject.left.name} of type #{left_type} can not be used with #{subject.right.name} of type #{right_type}")
    end

    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end
end