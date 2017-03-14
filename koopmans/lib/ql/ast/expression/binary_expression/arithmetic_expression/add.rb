module QL
  module AST
    class Add < ArithmeticExpression
      def eval(left, right)
        IntegerLiteral.new(left + right)
      end
    end
  end
end