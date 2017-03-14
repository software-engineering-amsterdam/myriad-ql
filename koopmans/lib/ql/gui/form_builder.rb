module QL
  module GUI
    class FormBuilder
      def visit_form(form)
        form.statements.map { |statement| statement.accept(self) }.flatten
      end

      def visit_if_statement(if_statement, condition=nil)
        condition = stack_conditions(condition, if_statement.condition)
        if_statement.body.map { |statement| statement.accept(self, condition) }
      end

      # stack conditions if possible
      def stack_conditions(condition_1, condition_2)
        if condition_1 && condition_2
          AST::Expression.new([condition_1, AST::And.new(condition_2)])
        else
          condition_1 || condition_2
        end
      end

      def visit_question(question, condition=nil)
        QuestionFrame.new(question, condition)
      end

      def visit_computed_question(question, condition=nil)
        ComputedQuestionFrame.new(question, condition)
      end
    end
  end
end