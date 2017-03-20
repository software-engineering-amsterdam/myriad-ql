module QL
  module AST
    class Type
      def accept(visitor)
        visitor.visit_type(self)
      end
    end
  end
end
