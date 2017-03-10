module QL
  module AST
    class Type
    end

    class BooleanType < Type
      def self.accept_types
        [BooleanType]
      end

      def literal_type
        BooleanLiteral
      end

      # def question_frame
      #   QL::GUI::BooleanQuestionFrame
      # end

      def render_widget(question_frame)
        QL::GUI::RadioWidget.new(question_frame: question_frame, true_value: 'JAAAA', false_value: 'NEEEE')
        # CheckboxWidget.new(question: self)
        # DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
      end

      def widget
        QL::GUI::RadioWidget
      end
    end

    class IntegerType < Type
      def self.accept_types
        [IntegerType, MoneyType]
      end

      def literal_type
        IntegerLiteral
      end

      def render_widget(question_frame)
        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        QL::GUI::SpinboxWidget.new(question_frame: question_frame)
        # TextWidget.new(question_frame: self)
      end

      def widget
        QL::GUI::SpinboxWidget
      end
    end

    class DateType < Type
      def self.accept_types
        [DateType]
      end

      def literal_type
        IntegerLiteral
      end

      def render_widget(question_frame)
        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        QL::GUI::SpinboxWidget.new(question_frame: question_frame)
        # TextWidget.new(question_frame: self)
      end
    end

    class DecimalType < Type
      def self.accept_types
        [DecimalType]
      end

      def literal_type
        IntegerLiteral
      end

      def render_widget(question_frame)
        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        QL::GUI::SpinboxWidget.new(question_frame: question_frame)
        # TextWidget.new(question_frame: self)
      end
    end

    class StringType < Type
      def self.accept_types
        [StringType]
      end

      def literal_type
        StringLiteral
      end

      def render_widget(question_frame)
        QL::GUI::TextWidget.new(question_frame: question_frame)
      end
    end

    # TODO make money type allow +-/* operations
    class MoneyType < Type
      def self.accept_types
        [MoneyType, IntegerType]
      end

      def literal_type
        IntegerLiteral
      end

      def render_widget(question_frame)
        # SliderWidget.new(question_frame: self, minimum: 0, maximum: 10)
        QL::GUI::SpinboxWidget.new(question_frame: question_frame)
        # TextWidget.new(question_frame: self)
      end

      def widget
        QL::GUI::SpinboxWidget
      end
    end
  end
end