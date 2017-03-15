module Prophet
  module Collectors
    class DefinedIdentifiers
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
        node.identifier
      end

      alias :visit_question_with_value :visit_question
    end
  end
end
