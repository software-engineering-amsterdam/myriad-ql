module QLS
  module AST
    class Stylesheet
      attr_reader :variable, :pages

      def initialize(variable, pages)
        @variable = variable
        @pages    = pages
      end

      def accept(visitor, argument = nil)
        visitor.visit_stylesheet(self, argument)
      end
    end
  end
end
