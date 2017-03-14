module QL
  module AST
    class Type
      def ==(other_object)
        self.class == other_object.class
      end

      def accept(visitor)
        visitor.visit_type(self)
      end
    end
  end
end
