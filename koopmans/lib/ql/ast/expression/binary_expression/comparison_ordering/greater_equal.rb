module QL
  module AST
    class GreaterEqual < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left >= right)
      end
    end
  end
end