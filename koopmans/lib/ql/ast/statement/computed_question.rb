module QL
  module AST
    class ComputedQuestion < Question
      attr_reader :assignment

      def initialize(label, variable, type, assignment)
        super(label, variable, type)
        @assignment = assignment
      end

      # TODO
      def accept(visitor, condition=nil)
        if condition
          visitor.visit_computed_question(self, condition)
        else
          visitor.visit_computed_question(self)
        end
      end
    end
  end
end
