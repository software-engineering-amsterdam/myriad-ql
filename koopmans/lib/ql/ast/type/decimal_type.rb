module QL
  module AST
    class DecimalType < Type
      def accept(visitor)
        visitor.visit_decimal_type(self)
      end
    end
  end
end