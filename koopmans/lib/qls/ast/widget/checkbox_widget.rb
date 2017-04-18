module QLS
  module AST
    class CheckboxWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_checkbox_widget(self, argument)
      end
    end
  end
end
