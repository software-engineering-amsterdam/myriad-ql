module QL
  module AST
    class Or < BooleanExpression
      def eval(left, right)
        BooleanLiteral.new(left || right)
      end
    end
  end
end