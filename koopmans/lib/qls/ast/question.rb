module QLS
  module AST
    class Question
      attr_reader :properties

      def initialize(properties)
        @properties = properties
      end
    end
  end
end