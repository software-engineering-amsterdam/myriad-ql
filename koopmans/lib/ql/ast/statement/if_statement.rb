module QL
  module AST
    class IfStatement
      attr_reader :body, :condition

      def initialize(condition, body)
        @condition = condition
        @body = body
      end

      def accept(visitor, argument = nil)
        visitor.visit_if_statement(self, argument)
      end
    end
  end
end
