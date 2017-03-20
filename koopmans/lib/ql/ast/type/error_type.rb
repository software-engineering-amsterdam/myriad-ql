module QL
  module AST
    class ErrorType < Type
      def accept(visitor)
        visitor.visit_error_type(self)
      end
    end
  end
end
