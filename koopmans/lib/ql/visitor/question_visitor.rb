module QL
  module Visitor
    class QuestionVisitor < BaseVisitor
      # return question
      def visit_question(question)
        question
      end
    end
  end
end