module QL
  module TypeChecker
    class ExpressionVariableCollector
      def visit_form(form, collected_data=nil)
        form.statements.map { |statement| statement.accept(self) }
      end

      # nothing has to be done with a question
      def visit_question(_)
      end

      def visit_computed_question(computed_question)
        computed_question.assignment.accept(self)
      end

      # combine the visit of the condition and the visit of all statements of the if statement
      def visit_if_statement(if_statement)
        if_statement.condition.accept(self)
        if_statement.body.map { |statement| statement.accept(self) }
      end

      def visit_if_else_statement(if_else_statement)
        if_else_statement.condition.accept(self)
        if_body_questions = if_else_statement.if_body.map { |statement| statement.accept(self) }
        else_body_questions = if_else_statement.else_body.map { |statement| statement.accept(self) }
        [if_body_questions, else_body_questions]
      end

      # visit operation in expression
      def visit_expression(expression)
        if expression.expression.respond_to? :reduce
          expression.expression.reduce do |left, operation|
            operation.accept(left, self)
          end
        else
          expression.expression.accept(self)
        end
      end

      def visit_binary_expression(left, binary_expression)
        # check if left and binary_expression.expression are an array (of variables), if so, don't call .accept
        unless left.kind_of?(Array)
          left = left.accept(self)
        end
        if binary_expression.expression.kind_of?(Array)
          right = binary_expression.expression
        else
          right = binary_expression.expression.accept(self)
        end
        [left, right]
      end

      def visit_negation(negation)
        negation.expression.accept(self)
      end

      # nothing has to be done with a literal
      def visit_literal(_)
        []
      end

      def visit_variable(variable)
        [variable]
      end
    end
  end
end