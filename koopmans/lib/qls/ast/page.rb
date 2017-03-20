module QLS
  module AST
    class Page
      attr_reader :variable, :block

      def initialize(variable, block)
        @variable = variable
        @block    = block
      end

      def accept(visitor)
        visitor.visit_page(self)
      end
    end
  end
end
