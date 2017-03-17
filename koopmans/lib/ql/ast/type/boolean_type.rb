module QL
  module AST
    class BooleanType < Type
      def literal_type
        BooleanLiteral
      end

      def widget
        # QL::GUI::RadioWidget
        QL::GUI::CheckboxWidget
        # QL::GUI::DropdownWidget
      end
    end
  end
end
