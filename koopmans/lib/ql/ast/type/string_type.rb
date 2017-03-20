module QL
  module AST
    class StringType < Type
      def accept(visitor)
        visitor.visit_string_type(self)
      end
    end
  end
end
