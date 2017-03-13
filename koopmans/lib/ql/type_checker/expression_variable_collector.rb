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
        [if_statement.condition.accept(self), if_statement.body.map { |statement| statement.accept(self) }]
      end

      # visit the expression
      def visit_expression(expression)
        if expression.expression.respond_to? :reduce
          expression.expression.reduce do |left, operation|
            operation.accept(left, self)
          end
        else
          expression.expression.accept(self)
        end
      end

      # visit the negation
      def visit_negation(negation)
        negation.expression.accept(self)
      end

      def visit_binary_expression(left, binary_expression)
        left  = left.accept(self)
        right = binary_expression.expression.accept(self)
        [left, right]
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