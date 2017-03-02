module QLS
  module AST
    class Stylesheet
      attr_reader :variable, :pages

      def initialize(variable, pages)
        @variable = variable
        @pages    = pages
      end

      def accept(visitor, form)
        visitor.visit_stylesheet(self, form)
      end
    end
  end
end