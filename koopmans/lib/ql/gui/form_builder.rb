module QL
  module GUI
    class FormBuilder
      def initialize(ast, gui)
        @gui = gui
        visit_form(ast)
      end

      # gather all labels from all questions and check for duplicates
      def visit_form(subject)
        subject.statements.map { |statement| statement.accept_with_condition(self, nil) }.flatten
      end

      # if there is an if in an if block create an And with both conditions
      def visit_if_statement(if_statement, condition)
        if_statement.expression = AST::And.new(condition, if_statement.expression) if condition
        if_statement.block.map { |statement| statement.accept_with_condition(self, if_statement.expression) }
      end

      # create corresponding question for gui
      def visit_question(question, condition)
        question.condition = condition.accept(self) if condition
        question.assignment = question.assignment.accept(self) if question.assignment
        question.render(@gui)
      end

      # visit the calculations of both the left and right sides
      def visit_expression(expression)
        expression.left  = expression.left.accept(self)
        expression.right = expression.right.accept(self)
        expression
      end

      def visit_negation(negation)
        negation.expression = negation.expression.accept(self)
        negation
      end

      # change variable to tk variable for gui
      def visit_variable(variable)
        @gui.questions[variable.name].variable
      end
    end
  end
end