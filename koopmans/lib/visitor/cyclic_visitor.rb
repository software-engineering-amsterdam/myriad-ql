require_relative '../visitor/question_visitor'

class CyclicVisitor < QuestionVisitor
  # visit calculation for the assignment of the question if available
  def visit_question(question)
    {question.variable.name => visit_calculation(question.assignment).flatten.compact} if question.assignment
  end
end