module QLS
  module AST
    class Question
      attr_reader :variable, :properties

      def initialize(variable, properties)
        @variable   = variable
        p properties
        @properties = [properties].flatten.inject(:merge) if properties
      end

      def accept(visitor)
        visitor.visit_question(self)
      end
    end
  end
end