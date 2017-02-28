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
        subject.statements.map { |statement| visit_statement(statement, nil) }.flatten
      end

      # if there is an if in an if block create an And with both conditions
      def visit_if_statement(subject, condition)
        if condition
          condition = AST::And.new(condition, subject.expression)
        else
          condition = subject.expression
        end
        subject.block.map { |statement| visit_statement(statement, condition) }
      end

      # create corresponding question for gui
      def visit_question(question, condition)
        # set optional condition
        question.condition = condition if condition
        if question.assignment
          ComputedQuestion.new(gui:         gui,
                               label:       question.label,
                               id:          question.variable.name,
                               type:        question.type.class,
                               calculation: visit_calculation(question.assignment),
                               condition:   visit_calculation(question.condition))
        elsif question.type == AST::BooleanType
          BooleanQuestion.new(gui:       gui,
                              label:     question.label,
                              id:        question.variable.name,
                              condition: visit_calculation(question.condition))
        elsif question.type == AST::MoneyType || question.type == AST::IntegerType
          NumericQuestion.new(gui:       gui,
                              label:     question.label,
                              id:        question.variable.name,
                              condition: visit_calculation(question.condition))
        elsif question.type == AST::StringType
          StringQuestion.new(gui:       gui,
                             label:     question.label,
                             id:        question.variable.name,
                             condition: visit_calculation(question.condition))
        end
      end

      # is it a question or an if statement?
      def visit_statement(statement, condition)
        if statement.kind_of?(AST::Question)
          visit_question(statement, condition)
        elsif statement.kind_of?(AST::IfStatement)
          visit_if_statement(statement, condition)
        end
      end

      # is the calculation a literal, a variable or an expression?
      def visit_calculation(calculation)
        return unless calculation
        if calculation.kind_of?(AST::Variable)
          visit_variable(calculation)
        elsif calculation.kind_of?(AST::BinaryExpression)
          visit_binary_expression(calculation)
        elsif calculation.kind_of?(AST::Negation)
          visit_negation(calculation)
        else
          calculation
        end
      end

      # visit the calculations of both the left and right sides
      def visit_binary_expression(subject)
        subject.left  = visit_calculation(subject.left)
        subject.right = visit_calculation(subject.right)
        subject
      end

      def visit_negation(subject)
        subject.expression = visit_calculation(subject.expression)
        subject
      end

      # change variable to tk variable for gui
      def visit_variable(subject)
        gui.questions[subject.name].variable
      end
    end
  end
end