module QL
  module GUI
    class Variable < TkVariable
      attr_accessor :type

      def eval
        case
          when type == AST::BooleanType
            bool
          when type == AST::StringType
            string
          when type == AST::IntegerType
            numeric
          when type == AST::MoneyType
            numeric
          else
            value
        end
      end

      def accept(_)
        self
      end
    end
  end
end