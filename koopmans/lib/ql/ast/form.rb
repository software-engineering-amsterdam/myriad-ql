module QL
  module AST
    class Form
      attr_reader :variable, :statements

      def initialize(variable, statements)
        @variable = variable
        @statements = statements
      end

      def accept(visitor)
        visitor.visit_form(self)
      end
    end
  end
end
