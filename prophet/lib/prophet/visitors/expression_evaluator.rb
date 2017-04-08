module Prophet
  module Visitors
    class ExpressionEvaluator
      def initialize(context)
        @context = context
      end

      def visit_logical_and(node)
        node.left.visit(self) && node.right.visit(self)
      end

      def visit_logical_or(node)
        node.left.visit(self) || node.right.visit(self)
      end

      def visit_equal(node)
        node.left.visit(self) == node.right.visit(self)
      end

      def visit_not_equal(node)
        node.left.visit(self) != node.right.visit(self)
      end

      def visit_less_then_or_equal(node)
        node.left.visit(self) <= node.right.visit(self)
      end

      def visit_less_then(node)
        node.left.visit(self) < node.right.visit(self)
      end

      def visit_greater_then(node)
        node.left.visit(self) > node.right.visit(self)
      end

      def visit_greater_then_or_equal(node)
        node.left.visit(self) >= node.right.visit(self)
      end

      def visit_multiplication(node)
        node.left.visit(self) * node.right.visit(self)
      end

      def visit_division(node)
        node.left.visit(self) / node.right.visit(self)
      end

      def visit_addition(node)
        node.left.visit(self) + node.right.visit(self)
      end

      def visit_subtraction(node)
        node.left.visit(self) - node.right.visit(self)
      end

      def visit_negation(node)
        !(node.value.visit(self))
      end

      def visit_identifier(node)
        context[node.name.to_s]
      end

      def visit_text_literal(node)
        node.value.to_s
      end

      def visit_number_literal(node)
        node.value.to_i
      end

      def visit_bool_literal(node)
        node.value == 'true'
      end

      private

      attr_reader :context
    end
  end
end
