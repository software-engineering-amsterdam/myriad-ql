module QL
  module AST
    class DateType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::SpinboxWidget
      end
    end
  end
end
