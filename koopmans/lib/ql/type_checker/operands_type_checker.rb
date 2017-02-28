module QL
  module TypeChecker
    class OperandsTypeChecker
      include Visitor
      include AST

      def visit_form(form)
        # get all variables and their types as defined by the questions
        # e.g. {"hasSoldHouse"=>#<BooleanType:0x007f959593fb70>, "hasBoughtHouse"=>#<BooleanType:0x007f9594969ac0>}
        @types = form.accept(QuestionVisitor.new).map { |question| [question.variable.name, question.type] }.to_h

        # do the actual operands type checking
        form.statements.map { |statement| statement.accept(self) }.flatten.compact
      end

      # visit calculation of the question
      def visit_question(question)
        question.assignment.accept(self) if question.assignment
      end

      # visit calculation of if condition and visit all statements in if block
      def visit_if_statement(if_statement)
        errors = []
        errors.push(if_statement.expression.accept(self))
        errors.push(if_statement.block.map { |statement| statement.accept(self) })
      end

      # variable is useless here
      def visit_variable(_)
      end

      # nothing has to be done with a literal
      def visit_literal(_)
      end

      def visit_negation(negation)
        expression_type = type(negation.expression)
        error(negation.class, expression_type) if ([type(negation).accept_types].flatten & [expression_type.accept_types].flatten).empty?
      end

      # an expression is checked for correctness
      def visit_expression(expression)
        left_type          = type(expression.left)
        right_type         = type(expression.right)
        correct_comparison = false

        errors = []
        # the left side does not match the operator
        errors.push(error(left_type, expression.class)) unless expression.accept_types.include? left_type
        # the right side does not match the operator
        errors.push(error(right_type, expression.class)) unless expression.accept_types.include? right_type
        # do the left and right side match?
        correct_comparison = left_type.accept_types.include? right_type if left_type
        errors.push(error(left_type, right_type)) unless correct_comparison

        errors.push([expression.left.accept(self), expression.right.accept(self)])
      end

      # get the type of a variable or other
      def type(left_or_right)
        if left_or_right.kind_of?(Variable)
          @types[left_or_right.name]
        else
          left_or_right.accept_types.first
        end
      end

      # generate error message
      def error(left, right)
        left  = 'undefined' unless left
        right = 'undefined' unless right
        "[ERROR]: #{left} can not be used with #{right}"
      end
    end
  end
end