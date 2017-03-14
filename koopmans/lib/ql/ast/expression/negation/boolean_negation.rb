module QL
  module AST
    class BooleanNegation < Negation
      def eval(expression)
        BooleanLiteral.new(!expression)
      end

      def is_compatible_with
        [BooleanType.new]
      end

      def return_type(type=nil)
        BooleanType.new
      end
    end
  end
end
