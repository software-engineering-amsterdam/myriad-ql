module QLS
  module AST
    class Default
      attr_reader :type, :properties

      def initialize(type, properties)
        @type       = type
        @properties = properties if properties
      end

      def accept(visitor, parent_default = nil)
        visitor.visit_default(self, parent_default)
      end
    end
  end
end
