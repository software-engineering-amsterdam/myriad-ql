module QL
  module GUI
    class FormBuilder
      attr_reader :question_frames

      def initialize
        @question_frames = []
      end

      def visit_form(form, _)
        form.statements.each { |statement| statement.accept(self) }
      end

      def visit_if_statement(if_statement, condition)
        condition = stack_conditions(condition, if_statement.condition)
        if_statement.body.each { |statement| statement.accept(self, condition) }
      end

      def visit_if_else_statement(if_else_statement, condition)
        if_condition = stack_conditions(condition, if_else_statement.condition)
        else_condition = stack_conditions(condition, negate(if_else_statement.condition))
        if_else_statement.if_body.each { |statement| statement.accept(self, if_condition) }
        if_else_statement.else_body.each { |statement| statement.accept(self, else_condition) }
      end

      def visit_question(question, condition)
        name = question.variable.name
        label = Label.new(question.label.value)
        literal_type, widget = question.type.accept(self)
        @question_frames << QuestionFrame.new(name: name, label: label, literal_type: literal_type, widget: widget, condition: condition)
      end

      def visit_computed_question(question, condition)
        name = question.variable.name
        label = Label.new(question.label.value)
        literal_type, = question.type.accept(self)
        widget = ComputedWidget.new
        assignment = question.assignment
        @question_frames << ComputedQuestionFrame.new(name: name, label: label, literal_type: literal_type, widget: widget, condition: condition, assignment: assignment)
      end

      def visit_boolean_type(_)
        [AST::BooleanLiteral, RadioWidget.new]
      end

      def visit_date_type(_)
        [AST::IntegerLiteral, TextWidget.new]
      end

      def visit_decimal_type(_)
        [AST::IntegerLiteral, TextWidget.new]
      end

      def visit_integer_type(_)
        [AST::IntegerLiteral, SpinboxWidget.new]
      end

      def visit_money_type(_)
        [AST::IntegerLiteral, SpinboxWidget.new]
      end

      def visit_string_type(_)
        [AST::StringLiteral, TextWidget.new]
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
    end
  end
end
