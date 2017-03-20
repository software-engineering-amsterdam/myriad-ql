module QL
  module GUI
    class FormBuilder
      def visit_form(form, collected_data=nil)
        form.statements.map { |statement| statement.accept(self) }.flatten
      end

      def visit_if_statement(if_statement, condition=nil)
        condition = stack_conditions(condition, if_statement.condition)
        if_statement.body.map { |statement| statement.accept(self, condition) }
      end

      def visit_if_else_statement(if_else_statement, condition=nil)
        if_condition = stack_conditions(condition, if_else_statement.condition)
        else_condition = stack_conditions(condition, negate(if_else_statement.condition))
        if_body_questions = if_else_statement.if_body.map { |statement| statement.accept(self, if_condition) }
        else_body_questions = if_else_statement.else_body.map { |statement| statement.accept(self, else_condition) }
        [if_body_questions, else_body_questions]
      end

      # negate for condition for else body of if else statement
      def negate(condition)
        AST::BooleanNegation.new('!', condition)
      end

      # stack conditions if possible for nested if (else) statements
      def stack_conditions(condition_1, condition_2)
        if condition_1 && condition_2
          AST::ExpressionSequence.new([condition_1, AST::ArithmeticExpression.new('&&', condition_2)])
        else
          condition_1 || condition_2
        end
      end

      def visit_question(question, condition=nil)
        name = question.variable.name
        label = question.label.value
        type, widget_type = question.type.accept(self)
        QuestionFrame.new(name, label, type, widget_type, condition)
      end

      def visit_computed_question(question, condition=nil)
        name = question.variable.name
        label = question.label.value
        type, _ = question.type.accept(self)
        widget_type = ComputedWidget
        assignment = question.assignment
        ComputedQuestionFrame.new(name, label, type, widget_type, condition, assignment)
      end

      def visit_boolean_type(_)
        return AST::BooleanLiteral, RadioWidget
      end

      def visit_date_type(_)
        return AST::IntegerLiteral, TextWidget
      end

      def visit_decimal_type(_)
        return AST::IntegerLiteral, TextWidget
      end

      def visit_integer_type(_)
        return AST::IntegerLiteral, SpinboxWidget
      end

      def visit_money_type(_)
        return AST::IntegerLiteral, SpinboxWidget
      end

      def visit_string_type(_)
        return AST::StringLiteral, TextWidget
      end

      # all widgets
      # TextWidget
      # SpinboxWidget
      # SliderWidget
      # RadioWidget
      # CheckboxWidget
      # DropdownWidget
    end
  end
end