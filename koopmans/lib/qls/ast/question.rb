module QLS
  module AST
    class Question
      attr_reader :variable, :properties

      def initialize(variable, properties)
        @variable   = variable
        @properties = properties if properties
      end
    end
  end
end