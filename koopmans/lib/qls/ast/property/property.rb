module QLS
  module AST
    class Property
      attr_reader :value

      def initialize(value)
        @value = value
      end

      def accept(visitor, argument = nil)
        visitor.visit_property(self, argument)
      end
    end
  end
end
