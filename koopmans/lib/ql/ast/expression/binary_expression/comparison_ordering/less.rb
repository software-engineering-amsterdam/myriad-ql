module QL
  module AST
    class Less < ComparisonOrdering
      def eval(left, right)
        BooleanLiteral.new(left < right)
      end
    end
  end
end