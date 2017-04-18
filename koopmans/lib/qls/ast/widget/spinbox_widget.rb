module QLS
  module AST
    class SpinboxWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_spinbox_widget(self, argument)
      end
    end
  end
end
