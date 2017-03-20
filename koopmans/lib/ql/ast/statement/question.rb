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
        visitor.visit_question(self, condition)
      end
    end
  end
end