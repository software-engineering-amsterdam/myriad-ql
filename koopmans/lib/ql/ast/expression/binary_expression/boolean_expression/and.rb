module QL
  module AST
    class And < BooleanExpression
      def eval(left, right)
        BooleanLiteral.new(left && right)
      end
    end
  end
end
