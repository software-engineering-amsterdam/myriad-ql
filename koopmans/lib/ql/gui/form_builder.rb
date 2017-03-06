module QL
  module GUI
    class FormBuilder
      attr_accessor :gui

      def initialize(ast, gui)
        @gui = gui
        visit_form(ast)
      end

      # gather all labels from all questions and check for duplicates
      def visit_form(subject)
        subject.statements.map { |statement| statement.accept_two_vars(self, nil) }.flatten
      end

      # if there is an if in an if block create an And with both conditions
      def visit_if_statement(subject, condition)
        if condition
          condition = AST::And.new(condition, subject.expression)
        else
          condition = subject.expression
        end
        subject.block.map { |statement| statement.accept_two_vars(self, condition) }
      end

      # create corresponding question for gui
      def visit_question(question, condition)
        # set optional condition
        question.condition = condition if condition

        if question.condition
          condition = question.condition.accept(self)
        else
          condition = nil
        end

        if question.assignment
          question.assignment = question.assignment.accept(self)
        end

        question.render(gui)

        # if question.assignment
        #   ComputedQuestion.new(gui:         gui,
        #                        label:       question.label,
        #                        id:          question.variable.name,
        #                        type:        question.type.class,
        #                        calculation: question.assignment.accept(self),
        #                        condition:   condition)
        # elsif question.type == AST::BooleanType
        #   BooleanQuestion.new(gui:       gui,
        #                       label:     question.label,
        #                       id:        question.variable.name,
        #                       condition: condition)
        # elsif question.type == AST::MoneyType || question.type == AST::IntegerType
        #   NumericQuestion.new(gui:       gui,
        #                       label:     question.label,
        #                       id:        question.variable.name,
        #                       condition: condition)
        # elsif question.type == AST::StringType
        #   StringQuestion.new(gui:       gui,
        #                      label:     question.label,
        #                      id:        question.variable.name,
        #                      condition: condition)
        # end
      end

      # visit the calculations of both the left and right sides
      def visit_expression(expression)
        expression.left  = expression.left.accept(self)
        expression.right = expression.right.accept(self)
        expression
      end

      def visit_negation(negation)
        negation.expression = negation.expression.accept(self)
        negation
      end

      # change variable to tk variable for gui
      def visit_variable(subject)
        gui.questions[subject.name].variable
      end
    end
  end
end