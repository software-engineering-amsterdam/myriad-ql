module QLS
  module AST
    class RadioWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_radio_widget(self, argument)
      end
    end
  end
end
