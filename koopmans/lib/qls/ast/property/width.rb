module QLS
  module AST
    class Width < Property
      def accept(visitor, argument = nil)
        visitor.visit_width(self, argument)
      end
    end
  end
end
