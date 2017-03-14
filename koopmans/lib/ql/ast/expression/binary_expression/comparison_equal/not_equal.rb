module QL
  module AST
    class NotEqual < ComparisonEqual
      def eval(left, right)
        BooleanLiteral.new(left != right)
      end
    end
  end
end
