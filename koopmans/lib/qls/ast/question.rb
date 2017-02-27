module QLS
  module AST
    class Question
      attr_reader :variable
      attr_reader :properties

      def initialize(variable, properties)
        @variable = variable
        @properties = properties if properties
      end
    end
  end
end