require 'parslet'
module QL
  module AST
    class Form
      attr_reader :variable
      attr_reader :statements

      def initialize(variable, statements)
        @variable = variable
        @statements = statements
      end

      # go through each element and 'visit' it
      def accept(visitor)
        visitor.visit_form(self)
      end
    end
  end
end