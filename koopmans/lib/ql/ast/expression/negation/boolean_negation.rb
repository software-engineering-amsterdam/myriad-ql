module QL
  module AST
    class BooleanNegation < Negation
      def accept(visitor)
        visitor.visit_boolean_negation(self)
      end

      # def eval(expression)
      #   BooleanLiteral.new(!expression)
      # end

      def is_compatible_with
        [BooleanType.new]
      end

      def return_type(type=nil)
        BooleanType.new
      end
    end
  end
end
