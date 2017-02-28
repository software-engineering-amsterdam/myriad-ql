module QLS
  module AST
    class Default
      attr_reader :type, :properties

      def initialize(type, properties)
        @type       = type
        @properties = properties
      end

      def accept(visitor)
        visitor.visit_default(self)
      end
    end
  end
end

