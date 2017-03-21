module QLS
  module AST
    class Stylesheet
      attr_reader :variable, :pages

      def initialize(variable, pages)
        @variable = variable
        @pages    = pages
      end

      def accept(visitor, collected_data = nil)
        visitor.visit_stylesheet(self, collected_data)
      end
    end
  end
end