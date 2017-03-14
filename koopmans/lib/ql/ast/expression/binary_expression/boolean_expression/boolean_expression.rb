module QL
  module AST
    # booleans: && ||
    class BooleanExpression < BinaryExpression
      def is_compatible_with
        [BooleanType.new]
      end

      def return_type(type=nil)
        BooleanType.new
      end
    end
  end
end
