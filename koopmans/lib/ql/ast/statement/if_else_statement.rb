module QL
  module AST
    class IfElseStatement
      attr_reader :if_body, :else_body, :condition

      def initialize(condition, if_body, else_body)
        @condition = condition
        @if_body = if_body
        @else_body = else_body
      end

      def accept(visitor, argument = nil)
        visitor.visit_if_else_statement(self, argument)
      end
    end
  end
end
