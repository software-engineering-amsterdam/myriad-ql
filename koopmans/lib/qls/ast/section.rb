module QLS
  module AST
    class Section
      attr_reader :name, :body

      def initialize(name, body)
        @name  = name
        @body = body
      end

      def accept(visitor)
        visitor.visit_section(self)
      end
    end
  end
end