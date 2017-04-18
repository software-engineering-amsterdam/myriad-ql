module QLS
  module AST
    class Font < Property
      def accept(visitor, argument = nil)
        visitor.visit_font(self, argument)
      end
    end
  end
end
