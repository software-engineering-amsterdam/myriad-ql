module QL
  module AST
    # comparisons: < > <= >=
    class ComparisonOrdering < BinaryExpression
      def is_compatible_with
        [IntegerType.new, MoneyType.new]
      end

      def return_type(type=nil)
        BooleanType.new
      end
    end
  end
end
