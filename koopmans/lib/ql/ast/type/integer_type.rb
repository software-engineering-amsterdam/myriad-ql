module QL
  module AST
    class IntegerType < Type
      def accept(visitor)
        visitor.visit_integer_type(self)
      end
    end
  end
end
