module QL
  module AST
    class BinaryExpression
      attr_accessor :operator, :expression

      def initialize(operator, expression)
        @operator = operator.to_s
        @expression = expression
      end
    end
  end
end
