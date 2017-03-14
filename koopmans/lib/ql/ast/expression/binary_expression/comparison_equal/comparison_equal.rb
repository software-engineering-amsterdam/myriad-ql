module QL
  module AST
    # comparisons == !=
    class ComparisonEqual < BinaryExpression
      def is_compatible_with
        [BooleanType.new, IntegerType.new, StringType.new, MoneyType.new]
      end

      def return_type(type=nil)
        BooleanType.new
      end
    end
  end
end
