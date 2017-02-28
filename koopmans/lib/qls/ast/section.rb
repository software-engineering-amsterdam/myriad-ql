module QLS
  module AST
    class Section
      attr_reader :name, :block

      def initialize(name, block)
        @name  = name
        @block = block
      end

      def accept(visitor)
        visitor.visit_section(self)
      end
    end
  end
end