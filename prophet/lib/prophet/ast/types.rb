module Prophet
  module Ast
    class Type < Node.new(:name)
      def name
        node_name.sub '_type', ''
      end
    end

    class TextType < Type
      def default_value
        ''
      end

      def associated_widget
        Widgets::Text
      end
    end

    class NumberType < Type
      def default_value
        0
      end

      def associated_widget
        Widgets::Text
      end
    end

    class BoolType < Type
      def default_value
        false
      end

      def associated_widget
        Widgets::Radio
      end
    end

    class UndefinedType < Type
    end
  end
end
