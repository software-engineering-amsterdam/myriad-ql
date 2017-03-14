module QL
  module AST
    class Form
      attr_reader :variable
      attr_reader :statements

      def initialize(variable, statements)
        @variable   = variable
        @statements = statements
      end

      def accept(visitor, collected_data=nil)
        visitor.visit_form(self, collected_data)
      end
    end
  end
end
