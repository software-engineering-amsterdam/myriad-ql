module QL
  module Visitor
    class ExpressionVariableCollector
      include Visitor
      include Notification

      # visit all statements of the form
      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }
      end

      # nothing has to be done with a question
      def visit_question(_)
      end

      # visit the assignment of a computed question
      def visit_computed_question(computed_question)
        computed_question.assignment.accept(self)
      end

      # combine the visit of the condition and the visit of all statements of the if statement
      def visit_if_statement(if_statement)
        if_statement.condition.accept(self) + if_statement.body.map { |statement| statement.accept(self) }
      end

      # visit the expression
      def visit_expression(expression)
        expression.expression.map { |expression| expression.accept(self) }
      end

      # visit the negation
      def visit_negation(negation)
        negation.expression.map{|negation| negation.accept(self)}
      end

      # literal should return empty array
      def visit_literal(_)
        []
      end

      # return variable
      def visit_variable(variable)
        variable
      end
    end
  end
end