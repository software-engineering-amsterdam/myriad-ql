module QL
  module AST
    class Negation < Expression
      def accept(visitor)
        visitor.visit_negation(self)
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
