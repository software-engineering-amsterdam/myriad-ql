module QL
  module TypeChecker
    class OperandsTypeChecker
      include Visitor
      include AST
      include Notification

      def visit_form(form, variable_type_hash)
        @variable_type_hash = variable_type_hash

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

      def visit_negation(negation)
        []
        # TODO
        # expression_type = type(negation.expression)
        # error(negation.class, expression_type) if ([type(negation).accept_types].flatten & [expression_type.accept_types].flatten).empty?
      end

      def visit_variable(variable)
        @variable_type_hash[variable.name].class
      end

      def visit_literal(literal)
        # pp 'joe'
        # pp [literal.accept_types.first]
        literal.to_type
      end

      # an expression is checked for correctness
      def visit_expression(expression)
        pp '---------------------------'
        # pp expression
        # expression_type = expression.expression.map{|e| type(e)}
        # pp expression_type
        # pp '================================'
        # []
        # left_type          = type(expression.left)
        # right_type         = type(expression.right)
        # correct_comparison = false
        #
        # errors = []
        # # the left side does not match the operator
        # errors.push(error(left_type, expression.class)) unless expression.accept_types.include? left_type
        # # the right side does not match the operator
        # errors.push(error(right_type, expression.class)) unless expression.accept_types.include? right_type
        # # do the left and right side match?
        # correct_comparison = left_type.accept_types.include? right_type if left_type
        # errors.push(error(left_type, right_type)) unless correct_comparison
        #
        # errors.push([expression.left.accept(self), expression.right.accept(self)])
        pp '1'
        # pp expression
        # pp expression.accept_types
        expression.expression.reduce { |left, operation| operation.type_check(left) }
        # pp expression.eval
        # types = expression.expression.map { |expression| expression.accept(self) }

        #
        # pp 'aap'
        # pp expression
        # pp types
        # pp expression.accept_types
        # pp (types - expression.accept_types).empty?


        pp '2'
      end

      # get the type of a variable or other
      # def type(expression)
      #   if expression.kind_of?(Variable)
      #     @variable_type_hash[expression.name]
      #   else
      #     expression.accept_types.first
      #   end
      # end

      # # generate error message
      # def error(left, right)
      #   left  = 'undefined' unless left
      #   right = 'undefined' unless right
      #   Notification.new("#{left} can not be used with #{right}")
      # end
    end
  end
end