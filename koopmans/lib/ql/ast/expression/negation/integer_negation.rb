module QL
  module AST
    class IntegerNegation < Negation
      def eval(expression)
        IntegerLiteral.new(-expression)
      end

      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end

      def return_type(type=nil)
        IntegerType.new
      end
    end
  end
end
