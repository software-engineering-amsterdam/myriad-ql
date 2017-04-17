module Prophet
  module Ast
    # !! maybe module Literal and have Ast::Type::Integer instead
    class Type < Node.new(:name)
      def name
        node_name.sub '_type', ''
      end
    end

    class TextType < Type
      def associated_widget
        Widgets::Text
      end
    end

    class NumberType < Type
      def associated_widget
        Widgets::Text
      end
    end

    class BoolType < Type
      def associated_widget
        Widgets::Radio
      end
    end

    class UndefinedType < Type
    end
  end
end
