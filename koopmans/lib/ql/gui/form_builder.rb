module QL
  module GUI
    class FormBuilder
      def initialize(ast, gui)
        @gui = gui
        visit_form(ast)
      end

      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }.flatten
      end

      # stack if conditions if possible
      def visit_if_statement(if_statement, condition=nil)
        if condition
          condition = AST::Expression.new([condition, AST::And.new(if_statement.condition)])
        end
        if_statement.body.map { |statement| statement.accept(self, condition) }
      end

      # render question in gui
      def visit_question(question, condition=nil)
        question.render(@gui, condition)
      end
    end
  end
end