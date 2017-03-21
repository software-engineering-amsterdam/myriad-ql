module QLS
  module AST
    class Page
      attr_reader :variable, :body

      def initialize(variable, body)
        @variable = variable
        @body    = body
      end

      def accept(visitor)
        visitor.visit_page(self)
      end
    end
  end
end
