module QLS
  module AST
    class Property
      attr_reader :value

      def initialize(value)
        @value = value
      end

      def accept(visitor, argument = nil)
        visitor.visit_property(self, argument)
      end
    end

    class Width < Property
      def accept(visitor, argument = nil)
        visitor.visit_width(self, argument)
      end
    end

    class Font < Property
      def accept(visitor, argument = nil)
        visitor.visit_font(self, argument)
      end
    end

    class Fontsize < Property
      def accept(visitor, argument = nil)
        visitor.visit_fontsize(self, argument)
      end
    end

    class Color < Property
      def accept(visitor, argument = nil)
        visitor.visit_color(self, argument)
      end
    end
  end
end



