module QL
  module AST
    class Form
      attr_reader :variable
      attr_reader :statements

      def initialize(variable, statements)
        @variable   = variable
        @statements = statements
      end

      def accept(visitor, variable_type_hash=nil)
        if variable_type_hash
          visitor.visit_form(self, variable_type_hash)
        else
          visitor.visit_form(self)
        end
      end
    end
  end
end