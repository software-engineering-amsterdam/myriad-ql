module QL
  module AST
    class IntegerType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        # QL::GUI::SpinboxWidget
        QL::GUI::SliderWidget
      end
    end
  end
end
