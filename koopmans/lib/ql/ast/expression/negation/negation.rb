module QL
  module AST
    class Negation
      attr_accessor :operator, :expression

      def initialize(operator, expression)
        @operator = operator
        @expression = expression
      end

      def eval_type(expression)
        if self.is_compatible_with.include?(expression)
          expression
        else
          NotificationTable.store(Error.new("incompatible types at #{self}"))
        end
      end
    end
  end
end
