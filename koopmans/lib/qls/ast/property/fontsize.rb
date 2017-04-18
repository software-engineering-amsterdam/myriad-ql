module QLS
  module AST
    class Fontsize < Property
      def accept(visitor, argument = nil)
        visitor.visit_fontsize(self, argument)
      end
    end
  end
end
