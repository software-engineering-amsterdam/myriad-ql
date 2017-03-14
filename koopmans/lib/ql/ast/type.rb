module QL
  module AST
    class Type
      def ==(other_object)
        self.class == other_object.class
      end

      def accept(visitor)
        visitor.visit_type(self)
      end
    end

    class ErrorType < Type

    end

    class BooleanType < Type
      def literal_type
        BooleanLiteral
      end

      def widget
        QL::GUI::RadioWidget
        # QL::GUI::CheckboxWidget
        # QL::GUI::DropdownWidget
      end

    end

    class IntegerType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        # QL::GUI::SpinboxWidget
        QL::GUI::SliderWidget
      end
    end

    class DateType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::SpinboxWidget
      end
    end

    class DecimalType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::SpinboxWidget
      end
    end

    class StringType < Type
      def literal_type
        StringLiteral
      end

      def widget
        QL::GUI::TextWidget
      end
    end

    class MoneyType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::TextWidget
        # QL::GUI::SpinboxWidget
        # QL::GUI::SliderWidget
      end
    end
  end
end