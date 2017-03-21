module QL
  module AST
    class Form
      attr_reader :variable
      attr_reader :statements

      def initialize(variable, statements)
        @variable = variable
        @statements = statements
      end

      def accept(visitor, argument = nil)
        visitor.visit_form(self, argument)
      end
    end
  end
end
