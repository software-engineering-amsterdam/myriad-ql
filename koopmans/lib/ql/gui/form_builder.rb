module QL
  module GUI
    class FormBuilder
      def initialize(ast, gui)
        @gui = gui
        visit_form(ast)
      end

      def visit_form(form)
        form.statements.map { |statement| statement.accept_with_condition(self, nil) }.flatten
      end

      # stack if conditions
      def visit_if_statement(if_statement, condition)
        if_statement.expression = AST::And.new(condition, if_statement.expression) if condition
        if_statement.block.map { |statement| statement.accept(self, if_statement.expression) }
      end

      # render question in gui
      def visit_question(question, condition)
        question.condition = condition.accept(self) if condition
        # question.assignment = question.assignment.accept(self) if question.assignment
        question.render(@gui)
      end

      # def visit_expression(expression)
      #   expression.left  = expression.left.accept(self)
      #   expression.right = expression.right.accept(self)
      #   expression
      # end
      #
      # def visit_negation(negation)
      #   negation.expression = negation.expression.accept(self)
      #   negation
      # end
      #
      # # change variable to tk variable for gui
      # def visit_variable(variable)
      #   @gui.questions[variable.name].variable
      # end
    end
  end
end