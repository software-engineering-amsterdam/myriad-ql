module QL
  module AST
    class Type
    end

    class BooleanType < Type
      def self.accept_types
        [BooleanType]
      end
    end

    class IntegerType < Type
      def self.accept_types
        [IntegerType, MoneyType]
      end
    end

    class DateType < Type
      def self.accept_types
        [DateType]
      end
    end

    class DecimalType < Type
      def self.accept_types
        [DecimalType]
      end
    end

    class StringType < Type
      def self.accept_types
        [StringType]
      end
    end

    # TODO make money type allow +-/* operations
    class MoneyType < Type
      def self.accept_types
        [MoneyType, IntegerType]
      end
    end
  end
end