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
        @label      = label.to_s
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

      # TODO gaan we hier wat aan doen?
      # def accept_with_condition(visitor, condition)
      #   visitor.visit_question(self, condition)
      # end

      def render(gui, condition)
        @type.question_frame.new(gui, self, condition)
      end
    end

    class ComputedQuestion < Question
      attr_reader :assignment

      def initialize(label, variable, type, assignment)
        super(label, variable, type)
        @assignment = assignment
      end

      def render(gui, condition)
        QL::GUI::ComputedQuestionFrame.new(gui, self, condition)
      end
    end
  end
end



