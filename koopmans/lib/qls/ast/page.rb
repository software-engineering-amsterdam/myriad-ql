module QLS
  module AST
    class Page
      attr_reader :variable, :block

      def initialize(variable, block)
        @variable = variable
        @block    = block
      end
    end
  end
end