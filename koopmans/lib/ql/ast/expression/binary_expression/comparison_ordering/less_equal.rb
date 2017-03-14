module QL
  module AST
    class LessEqual < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left <= right)
      end
    end
  end
end
