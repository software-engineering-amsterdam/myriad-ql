require_relative '../visitor/question_visitor'

class CyclicVisitor < QuestionVisitor
  # visit calculation for the assignment of the question if available
  def visit_question(subject)
    {subject.variable.name => visit_calculation(subject.assignment).flatten.compact} if subject.assignment
  end
end