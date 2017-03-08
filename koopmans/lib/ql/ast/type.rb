module QL
  module AST
    class Type
    end

    class BooleanType < Type
      def self.accept_types
        [BooleanType]
      end

      def question_frame
        QL::GUI::BooleanQuestionFrame
      end
    end

    class IntegerType < Type
      def self.accept_types
        [IntegerType, MoneyType]
      end

      def question_frame
        QL::GUI::NumericQuestionFrame
      end
    end

    class DateType < Type
      def self.accept_types
        [DateType]
      end

      def question_frame
        QL::GUI::NumericQuestionFrame
      end
    end

    class DecimalType < Type
      def self.accept_types
        [DecimalType]
      end

      def question_frame
        QL::GUI::NumericQuestionFrame
      end
    end

    class StringType < Type
      def self.accept_types
        [StringType]
      end

      def question_frame
        QL::GUI::StringQuestionFrame
      end
    end

    # TODO make money type allow +-/* operations
    class MoneyType < Type
      def self.accept_types
        [MoneyType, IntegerType]
      end

      def question_frame
        QL::GUI::NumericQuestionFrame
      end
    end
  end
end