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

      # def render_widget(question_frame)
      #   # QL::GUI::RadioWidget.new(question_frame: question_frame, true_value: 'JAAAA', false_value: 'NEEEE')
      #   CheckboxWidget.new(question_frame: question_frame)
      #   # DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
      # end

      def widget
        QL::GUI::RadioWidget
        # QL::GUI::CheckboxWidget
        # QL::GUI::DropdownWidget
      end

      def default_value
        true
      end
    end

    class IntegerType < Type
      def literal_type
        IntegerLiteral
      end

      # def render_widget(question_frame)
      #   # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
      #   QL::GUI::SpinboxWidget.new(question_frame: question_frame)
      #   # TextWidget.new(question_frame: self)
      # end

      def widget
        # QL::GUI::SpinboxWidget
        QL::GUI::SliderWidget
      end

      def default_value
        0
      end
    end

    class DateType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::SpinboxWidget
      end

      def default_value
        0
      end
    end

    class DecimalType < Type
      def literal_type
        IntegerLiteral
      end

      def widget
        QL::GUI::SpinboxWidget
      end

      def default_value
        0.0
      end
    end

    class StringType < Type
      def literal_type
        StringLiteral
      end

      def widget
        QL::GUI::TextWidget
      end

      def default_value
        ''
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

      def default_value
        0
      end
    end
  end
end