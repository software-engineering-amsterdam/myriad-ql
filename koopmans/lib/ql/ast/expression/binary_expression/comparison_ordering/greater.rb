module QL
  module AST
    class Greater < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left > right)
      end
    end
  end
end
