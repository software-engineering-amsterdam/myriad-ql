module Prophet
  module Collectors
    class UsedIdentifiers
      def visit_form(node)
        node.body.collect { |child| child.visit self }
      end

      def visit_if_statement(node)
        [node.condition.visit(self)] + node.true_branch.collect { |child| child.visit self }
      end

      def visit_if_else_statement(node)
        [node.condition.visit(self)] + (node.true_branch + node.false_branch).collect { |child| child.visit self }
      end

      def visit_question(node)
      end

      def visit_question_with_value(node)
        node.value.visit self
      end

      def visit_binary_expression(node)
        [node.left, node.right].collect { |child| child.visit self }
      end

      alias :visit_multiplication :visit_binary_expression
      alias :visit_division       :visit_binary_expression
      alias :visit_addition       :visit_binary_expression
      alias :visit_subtraction    :visit_binary_expression

      def visit_unary_expression(node)
        node.value.visit self
      end

      def visit_identifier(node)
        node
      end

      def visit_literal(node)
      end

      alias :visit_text_literal   :visit_literal
      alias :visit_number_literal :visit_literal
      alias :visit_bool_literal   :visit_literal
    end
  end
end
