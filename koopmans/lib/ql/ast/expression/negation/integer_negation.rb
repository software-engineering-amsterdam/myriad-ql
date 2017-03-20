module QL
  module AST
    class IntegerNegation < Negation
      def accept(visitor)
        visitor.visit_integer_negation(self)
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
