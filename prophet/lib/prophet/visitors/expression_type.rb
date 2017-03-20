module Prophet
  module Visitors
    class ExpressionType
      def initialize(type_mapping)
        @type_mapping = type_mapping
        @fallback = Ast::Type.new('undefined')
      end

      def visit_binary_expression(node)
        left_type = node.left.visit self
        right_type = node.right.visit self

        left_type == right_type ? left_type : fallback
      end

      alias :visit_multiplication :visit_binary_expression
      alias :visit_division       :visit_binary_expression
      alias :visit_addition       :visit_binary_expression
      alias :visit_subtraction    :visit_binary_expression

      def visit_identifier(node)
        type_mapping.fetch(node, fallback)
      end

      def visit_literal(node)
        node.associated_type
      end

      alias :visit_text_literal   :visit_literal
      alias :visit_number_literal :visit_literal
      alias :visit_bool_literal   :visit_literal

      private

      attr_reader :type_mapping, :fallback
    end
  end
end
