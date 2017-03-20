module Prophet
  module Collectors
    class QuestionsWithValue
      def visit_form(node)
        node.body.collect { |child| child.visit self }
      end

      def visit_if_statement(node)
        node.true_branch.collect { |child| child.visit self }
      end

      def visit_if_else_statement(node)
        (node.true_branch + node.false_branch).collect { |child| child.visit self }
      end

      def visit_question(node)
      end

      def visit_question_with_value(node)
        node
      end
    end
  end
end
