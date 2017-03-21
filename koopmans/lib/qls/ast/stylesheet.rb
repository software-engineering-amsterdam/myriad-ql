module QLS
  module AST
    class Stylesheet
      attr_reader :variable, :pages

      def initialize(variable, pages)
        @variable = variable
        @pages    = pages
      end

      def accept(visitor)
        visitor.visit_stylesheet(self)
      end
    end
  end
end