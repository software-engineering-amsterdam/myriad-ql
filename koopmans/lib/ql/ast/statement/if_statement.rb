module QL
  module AST
    class IfStatement
      attr_reader :body, :condition

      def initialize(condition, body)
        @condition = condition
        @body = body
      end

      def accept(visitor, condition=nil)
        visitor.visit_if_statement(self, condition)
      end
    end
  end
end
