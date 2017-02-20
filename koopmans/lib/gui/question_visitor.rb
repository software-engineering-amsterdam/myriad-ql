class QuestionVisitor
  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    questions = subject.statements.map { |statement| visit_statement(statement, nil) }.flatten
  end

  # visit all statements of the if block
  def visit_if_statement(subject, condition)
    if condition
      condition = And.new(condition, subject.expression)
    else
      condition = subject.expression
    end
    # {
    #     if: {
    #         condition: subject.expression,
    #         statements: subject.block.map { |statement| visit_statement(statement) }
    #     }
    # }
    # ["#{subject.expression}": subject.block.map { |statement| visit_statement(statement) }]

    subject.block.map { |statement| visit_statement(statement, condition) }
  end

  # return question
  def visit_question(subject, condition)
    subject.condition = condition
    subject
    # { question: subject }
    #
    # if condition
    #   {question: subject, condition: condition}
    #   # subject.condition = condition
    # else
    #   {question: subject}
    # end

  end

  # is it a question or an if statement?
  def visit_statement(statement, condition)
    if statement.kind_of?(Question)
      visit_question(statement, condition)
    elsif statement.kind_of?(IfStatement)
      visit_if_statement(statement, condition)
    else
      raise NotImplementedError
    end
  end

    # # is the calculation a literal, a variable or an expression?
    # def visit_calculation(calculation)
    #   if calculation.kind_of?(Literal)
    #     visit_literal(calculation)
    #   elsif calculation.kind_of?(Variable)
    #     visit_variable(calculation)
    #   elsif calculation.kind_of?(Expression)
    #     visit_expression(calculation)
    #   else
    #     raise NotImplementedError
    #   end
    # end
    #
    # # literal should return nil
    # def visit_literal(subject)
    #   subject
    # end
    #
    # # visit the calculations of both the left and right sides
    # def visit_expression(subject)
    #   [visit_calculation(subject.left), visit_calculation(subject.right)]
    # end
    #
    # def visit_variable(subject)
    #   subject
    # end
end