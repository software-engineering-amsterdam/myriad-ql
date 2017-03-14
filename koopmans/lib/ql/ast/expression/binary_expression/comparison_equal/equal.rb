module QL
  module AST
    class Equal < ComparisonEqual
      def eval(left, right)
        BooleanLiteral.new(left == right)
      end
    end
  end
end
