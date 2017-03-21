module QLS
  module AST
    class Property
      attr_reader :value

      def initialize(value)
        @value = value
      end

      def accept(visitor, type = nil)
        visitor.visit_property(self, type)
      end
    end

    class Width < Property
      def accept(visitor, type = nil)
        visitor.visit_width(self, type)
      end
    end

    class Font < Property
      def accept(visitor, type = nil)
        visitor.visit_font(self, type)
      end
    end

    class Fontsize < Property
      def accept(visitor, type = nil)
        visitor.visit_fontsize(self, type)
      end
    end

    class Color < Property
      def accept(visitor, type = nil)
        visitor.visit_color(self, type)
      end
    end
  end
end



