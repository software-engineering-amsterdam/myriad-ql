module QL
  module AST
    # arithmetic: - + * /
    class ArithmeticExpression < BinaryExpression
      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end

      def return_type(type=nil)
        type
      end
    end
  end
end