module QLS
  module AST
    class SliderWidget < Widget
      def accept(visitor, argument = nil)
        visitor.visit_slider_widget(self, argument)
      end
    end
  end
end
