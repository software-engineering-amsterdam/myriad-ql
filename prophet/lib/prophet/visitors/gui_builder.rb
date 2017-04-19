module Prophet
  module Visitors
    class GuiBuilder
      def initialize(values)
        @values = values
      end

      def visit_form(node)
        node.body.flat_map { |child| child.visit self }
      end

      def visit_if_statement(node)
        node.true_branch.flat_map { |child| child.visit self } if values[node]
      end

      def visit_if_else_statement(node)
        if values[node]
          node.true_branch.flat_map { |child| child.visit self }
        else
          node.false_branch.flat_map { |child| child.visit self }
        end
      end

      def visit_question(node)
        name = node.identifier.name.to_s
        label = Widgets::Label.new(name)
        input = node.type.class.associated_widget.new(name, values[name])
        Widgets::Fieldset.new(name, label, input)
      end

      def visit_question_with_value(node)
        name = node.identifier.name.to_s
        label = Widgets::Label.new(name)
        input = node.type.class.associated_widget.new(name, values[name], state: 'disabled')
        Widgets::Fieldset.new(name, label, input)
      end

      private

      attr_reader :values
    end
  end
end
