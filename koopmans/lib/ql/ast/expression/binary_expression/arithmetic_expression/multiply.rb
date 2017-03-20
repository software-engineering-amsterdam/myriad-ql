module QL
  module AST
    class Multiply < ArithmeticExpression
      # def eval(left, right)
      #   IntegerLiteral.new(left * right)
      # end
      def accept(left, visitor)
        visitor.visit_multiply(left, self)
      end
    end
  end
end
