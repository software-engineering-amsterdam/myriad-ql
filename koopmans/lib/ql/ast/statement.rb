module QL
  module AST
    class IfStatement
      # extend Parslet
      attr_reader :expression, :block

      def initialize(expression, block)
        @expression = expression
        @block      = block
      end

      def accept(visitor)
        visitor.visit_if_statement(self)
      end

      def accept_two_vars(visitor, condition)
        visitor.visit_if_statement(self, condition)
      end
    end

    class Question
      extend Parslet

      attr_reader :label, :variable, :type, :assignment
      attr_accessor :condition

      def initialize(label, variable, type, expression=nil, condition=nil)
        @label      = label.to_s
        @variable   = variable
        @type       = type
        @assignment = expression if expression
        @condition  = condition if condition
      end

      def accept(visitor)
        visitor.visit_question(self)
      end

      # TODO is going to be removed
      def accept_two_vars(visitor, condition)
        visitor.visit_question(self, condition)
      end

      def render(args)


      end
    end
  end
end



