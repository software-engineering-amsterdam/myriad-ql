module QL
  module AST
    class Question
      attr_reader :label, :variable, :type

      def initialize(label, variable, type)
        @label = label
        @variable = variable
        @type = type
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_question(self, condition)
        else
          visitor.visit_question(self)
        end
      end
    end
  end
end