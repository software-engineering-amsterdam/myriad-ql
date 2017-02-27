module QLS
  module AST
    class StyleSheet
      attr_reader :variable
      attr_reader :pages


      def initialize(variable, pages)
        @variable = variable
        @pages = pages
      end
    end
  end
end