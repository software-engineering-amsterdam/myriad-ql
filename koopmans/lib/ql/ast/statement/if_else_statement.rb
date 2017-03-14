module QL
  module AST
    class IfElseStatement
      attr_reader :if_body, :else_body, :condition

      def initialize(condition, if_body, else_body)
        @condition = condition
        @if_body = if_body
        @else_body = else_body
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_if_else_statement(self, condition)
        else
          visitor.visit_if_else_statement(self)
        end
      end
    end
  end
end
