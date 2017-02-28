module QLS
  module AST
    class Section
      attr_reader :name, :block

      def initialize(name, block)
        @name  = name
        @block = block
      end
    end
  end
end