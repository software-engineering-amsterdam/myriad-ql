module QLS
  module AST
    class Section
      attr_reader :name, :body

      def initialize(name, body)
        @name = name
        @body = body
      end

      def accept(visitor, argument = nil)
        visitor.visit_section(self, argument)
      end
    end
  end
end
