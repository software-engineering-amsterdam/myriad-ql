module QL
  module Visitor
    class CyclicVisitor < BaseCollector
      # visit calculation for the assignment of the question if available
      def visit_question(question)
        { question.variable.name => question.assignment.accept(self).flatten.compact } if question.assignment
      end
    end
  end
end