module QL
  module AST
    class IntegerNegation < Negation
      def accept(visitor)
        visitor.visit_integer_negation(self)
      end
    end
  end
end
