module QL
  module AST
    class MoneyType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        # QL::GUI::TextWidget
        QL::GUI::SpinboxWidget
        # QL::GUI::SliderWidget
      end
    end
  end
end
