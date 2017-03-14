module QL
  module AST
    class IfStatement
      attr_reader :body, :condition

      def initialize(condition, body)
        @condition = condition
        @body = body
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_if_statement(self, condition)
        else
          visitor.visit_if_statement(self)
        end
      end
    end
  end
end
