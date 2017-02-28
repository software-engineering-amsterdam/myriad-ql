module QLS
  module AST
    class StyleSheet
      attr_reader :variable, :pages

      def initialize(variable, pages)
        @variable = variable
        @pages    = pages
      end
    end
  end
end