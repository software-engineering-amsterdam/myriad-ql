module QL
  module AST
    class BooleanNegation < Negation
      def accept(visitor)
        visitor.visit_boolean_negation(self)
      end
    end
  end
end
