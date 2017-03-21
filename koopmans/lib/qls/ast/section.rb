module QLS
  module AST
    class Section
      attr_reader :name, :body

      def initialize(name, body)
        @name  = name
        @body = body
      end

      def accept(visitor, parent_default = nil)
        visitor.visit_section(self, parent_default)
      end
    end
  end
end