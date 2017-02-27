require_relative '../visitor/cyclic_visitor'

module QL
  module TypeChecker
    class CyclicChecker
      def visit_form(form)
        # get question variables with dependency variables as hash
        # e.g. {"sellingPrice"=>[#<Variable:0x007ff31ca431e0 @name="privateDebt">, #<Variable:0x007ff31ca4ae90 @name="var1">],
        #       "privateDebt"=>[#<Variable:0x007ff31e17eaf8 @name="sellingPrice">, #<Variable:0x007ff31e1868e8 @name="var2">]}
        @values = form.accept(CyclicVisitor.new).compact.inject(:merge)
        @errors = []

        # do the actual cyclic checking
        form.statements.map { |statement| statement.accept(self) }
        @errors.uniq
      end

      # visit all statements of the if block
      def visit_if_statement(if_statement)
        if_statement.block.map { |statement| statement.accept(self) }
      end

      # visit question, and visit calculation for the assignment of the question
      def visit_question(question)
        {question.variable.name => question.assignment.accept(self).flatten.compact} if question.assignment
      end

      # visit the calculation of the negation expression
      def visit_negation(negation)
        negation.expression.accept(self)
      end

      # visit the calculations of both the left and right sides
      def visit_expression(expression)
        [expression.left.accept(self), expression.right.accept(self)]
      end

      # nothing has to be done with a literal
      def visit_literal(_)
      end

      # check if the visited variable is in the dependency hash
      # for each of the dependencies, check their dependencies
      # check if the variable from the dependency is in the dependency hash
      # add new dependency to original dependency hash, don't add duplicates
      # check for cyclic dependency if there is a dependency on itself, else visit the next variable
      def visit_variable(variable)
        if @values.key? variable.name
          @values[variable.name].each do |k|
            if @values.key? k.name
              @values[variable.name] = @values[variable.name] | @values[k.name]
              if @values[variable.name].map(&:name).include? variable.name
                @errors.push("[ERROR]: question with variable '#{variable.name}' has a cyclic dependency")
              else
                visit_variable(k)
              end
            end
          end
        end
        []
      end
    end
  end
end