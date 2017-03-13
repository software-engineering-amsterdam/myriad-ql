module QL
  module AST
    class IfStatement
      attr_reader :body, :condition

      def initialize(condition, body)
        @condition = condition
        @body      = body
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_if_statement(self, condition)
        else
          visitor.visit_if_statement(self)
        end
      end

      # def accept_with_condition(visitor, condition)
      #   visitor.visit_if_statement(self, condition)
      # end
    end

    class Question
      attr_reader :label, :variable, :type

      def initialize(label, variable, type)
        @label      = label
        @variable   = variable
        @type       = type
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_question(self, condition)
        else
          visitor.visit_question(self)
        end
      end
    end

    class ComputedQuestion < Question
      attr_reader :assignment

      def initialize(label, variable, type, assignment)
        super(label, variable, type)
        @assignment = assignment
      end

      def accept(visitor, condition=nil)
        if condition
          visitor.visit_computed_question(self, condition)
        else
          visitor.visit_computed_question(self)
        end
      end
    end
  end
end



