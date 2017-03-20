module QL
  module AST
    class ComputedQuestion < Question
      attr_reader :assignment

      def initialize(label, variable, type, assignment)
        super(label, variable, type)
        @assignment = assignment
      end

      def accept(visitor, condition = nil)
        visitor.visit_computed_question(self, condition)
      end
    end
  end
end
