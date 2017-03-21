module QLS
  module AST
    class Question
      attr_reader :variable, :properties

      def initialize(variable, properties)
        @variable   = variable
        @properties = [properties].flatten.inject(:merge) if properties
      end

      def accept(visitor, parent_default = nil)
        visitor.visit_question(self, parent_default)
      end
    end
  end
end