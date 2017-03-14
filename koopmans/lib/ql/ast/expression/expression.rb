module QL
  module AST
    class Expression
      attr_reader :expression

      def initialize(expression)
        @expression = expression
      end

      def accept(visitor)
        visitor.visit_expression(self)
      end

      def eval_type(left_type, right_type)
        # return the left type if there are no errors and else return an error
        if self.is_compatible_with.include?(left_type) and
          self.is_compatible_with.include?(right_type) and
          left_type == right_type
          self.return_type(left_type)
        else
          NotificationTable.store(Notification::Error.new("incompatible types at #{self}"))
          ErrorType.new
        end
      end
    end
  end
end
