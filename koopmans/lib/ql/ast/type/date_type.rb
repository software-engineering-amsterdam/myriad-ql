module QL
  module AST
    class DateType < Type
      def accept(visitor)
        visitor.visit_date_type(self)
      end
    end
  end
end
