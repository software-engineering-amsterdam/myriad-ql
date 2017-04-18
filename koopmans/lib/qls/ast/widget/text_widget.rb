module QLS
  module AST
    class TextWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_text_widget(self, argument)
      end
    end
  end
end
