module Prophet
  module Visitors
    class ExpressionType
      def initialize(type_mapping)
        @type_mapping = type_mapping
        @fallback = Ast::UndefinedType.new
        @bool = Ast::BoolType.new
      end

      def visit_bool_expression(node)
        left_type = node.left.visit self
        right_type = node.right.visit self

        left_type == right_type ? bool : fallback
      end

      alias :visit_logical_and            :visit_bool_expression
      alias :visit_logical_or             :visit_bool_expression
      alias :visit_equal                  :visit_bool_expression
      alias :visit_not_equal              :visit_bool_expression
      alias :visit_less_then_or_equal     :visit_bool_expression
      alias :visit_less_then              :visit_bool_expression
      alias :visit_greater_then           :visit_bool_expression
      alias :visit_greater_then_or_equal  :visit_bool_expression

      def visit_arithmetic_expression(node)
        left_type = node.left.visit self
        right_type = node.right.visit self

        left_type == right_type ? left_type : fallback
      end

      alias :visit_multiplication :visit_arithmetic_expression
      alias :visit_division       :visit_arithmetic_expression
      alias :visit_addition       :visit_arithmetic_expression
      alias :visit_subtraction    :visit_arithmetic_expression

      def visit_negation(node)
        bool
      end

      def visit_identifier(node)
        type_mapping.fetch(node.name.to_s, fallback)
      end

      def visit_literal(node)
        node.class.associated_type
      end

      alias :visit_text_literal   :visit_literal
      alias :visit_number_literal :visit_literal
      alias :visit_bool_literal   :visit_literal

      private

      attr_reader :type_mapping, :fallback, :bool
    end
  end
end
