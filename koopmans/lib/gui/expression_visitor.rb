require_relative 'question_visitor'

class ExpressionVisitor
  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    @questions = subject.accept(QuestionVisitor.new)
    p @questions

    aap = subject.statements.map { |statement| visit_statement(statement) }.flatten
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # return question
  def visit_question(subject)
    visit_calculation(subject.assignment) if subject.assignment
  end

  # is it a question or an if statement?
  def visit_statement(statement)
    if statement.kind_of?(Question)
      visit_question(statement)
    elsif statement.kind_of?(IfStatement)
      visit_if_statement(statement)
    else
      raise NotImplementedError
    end
  end

  # is the calculation a literal, a variable or an expression?
  def visit_calculation(calculation)
    if calculation.kind_of?(Literal)
      visit_literal(calculation)
    elsif calculation.kind_of?(Variable)
      visit_variable(calculation)
    elsif calculation.kind_of?(Expression)
      visit_expression(calculation)
    else
      raise NotImplementedError
    end
  end

  # literal should return nil
  def visit_literal(subject)
    subject
  end

  # visit the calculations of both the left and right sides
  def visit_expression(subject)
    [visit_calculation(subject.left), visit_calculation(subject.right)]
  end

  def visit_variable(subject)
    p subject.name
    @questions[subject.name]
  end
end