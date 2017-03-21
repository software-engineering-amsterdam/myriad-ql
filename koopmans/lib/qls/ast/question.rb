module QLS
  module AST
    class Question
      attr_reader :variable, :properties

      def initialize(variable, properties)
        @variable = variable
        @properties = properties if properties
      end

      def accept(visitor, argument = nil)
        visitor.visit_question(self, argument)
      end
    end
  end
end
