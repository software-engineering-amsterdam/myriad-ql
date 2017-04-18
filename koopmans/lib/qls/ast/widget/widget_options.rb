module QLS
  module AST
    class WidgetOptions
      attr_reader :first_value, :second_value

      def initialize(first_value, second_value)
        @first_value = first_value
        @second_value = second_value
      end

      def accept(visitor)
        visitor.visit_widget_options(self)
      end
    end
  end
end