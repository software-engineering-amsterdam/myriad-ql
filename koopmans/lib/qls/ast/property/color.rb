module QLS
  module AST
    class Color < Property
      def accept(visitor, argument = nil)
        visitor.visit_color(self, argument)
      end
    end
  end
end
