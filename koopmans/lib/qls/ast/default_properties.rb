module QLS
  module AST
    class DefaultProperties
      attr_reader :type, :properties

      def initialize(type, properties)
        @type       = type
        @properties = properties if properties
      end

      def accept(visitor, argument = nil)
        visitor.visit_default_properties(self, argument)
      end
    end
  end
end
