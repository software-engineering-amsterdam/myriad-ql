module QL
  module AST
    class StringType < Type
      def literal_type
        StringLiteral
      end

      def widget
        QL::GUI::TextWidget
      end
    end
  end
end
