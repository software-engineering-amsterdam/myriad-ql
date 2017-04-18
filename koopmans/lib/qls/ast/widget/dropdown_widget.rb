module QLS
  module AST
    class DropdownWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_dropdown_widget(self, argument)
      end
    end
  end
end
