module QL
  module TypeChecker
    class OperandsTypeChecker
      include Visitor
      include AST
      include Notification

      def visit_form(form)
        # @variable_type_hash = variable_type_hash

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
        if_statement.condition.accept(self)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_negation(negation)
        negation.expression.eval_type
      end


      def visit_variable(_)
      end


      # an expression is checked for correctness
      def visit_expression(expression)
        expression.expression.reduce do |left, operation|
          left.accept(self)
          operation.accept(self)
        end
        pp 'jjjjjjjjj'
        # expression.expression.accept(self)
        # expression.eval_type
      end

      # def visit_binary_expression(expression)
      #   expression.expression.accept(self)
      # end

      def visit_literal(literal)
        pp literal
        pp 'visit literal'
        literal
      end

      def visit_add(add)
        pp '--------------------------------'
        pp add
      end
    end
  end
end