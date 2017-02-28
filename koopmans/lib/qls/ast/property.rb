module QLS
  module AST
    class Property
      attr_reader :value

      def initialize(value)
        @value = value
      end

      def accept(visitor)
        visitor.visit_property(self)
      end
    end

    class Width < Property

    end

    class Font < Property

    end

    class Fontsize < Property

    end

    class Color < Property

    end
  end
end



