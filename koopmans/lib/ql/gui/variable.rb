module QL
  module GUI
    class Variable < TkVariable
      attr_accessor :type

      def eval
        if type == AST::BooleanType
          bool
        elsif type == AST::StringType
          string
        elsif type == AST::IntegerType || type == AST::MoneyType
          numeric
        else
          value
        end
      end
    end
  end
end