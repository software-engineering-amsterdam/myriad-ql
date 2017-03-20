module QL
  module AST
    class MoneyType < Type
      def accept(visitor)
        visitor.visit_money_type(self)
      end
    end
  end
end
