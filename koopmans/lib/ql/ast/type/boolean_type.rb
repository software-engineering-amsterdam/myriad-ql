module QL
  module AST
    class BooleanType < Type
      def accept(visitor)
        visitor.visit_boolean_type(self)
      end
    end
  end
end
