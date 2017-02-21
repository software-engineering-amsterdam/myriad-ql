require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/variable'
require_relative '../ast/type'

require_relative 'gui_question'
require_relative 'boolean_question'
require_relative 'string_question'
require_relative 'integer_question'
require_relative 'computed_question'
require_relative 'gui_question_visitor'

require 'tk'
require 'pp'

class GUIQuestionVisitor
  attr_accessor :questions

  def initialize(ast)
    @questions = Hash.new
    visit_form(ast)
  end

  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    subject.statements.map { |statement| visit_statement(statement, nil) }.flatten
  end

  # if there is an if in an if block create an And with both conditions
  def visit_if_statement(subject, condition)
    if condition
      condition = And.new(condition, subject.expression)
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
      ComputedQuestion.new(gui: self,
                           label: question.label,
                           id: question.variable.name,
                           calculation: visit_calculation(question.assignment),
                           condition: visit_calculation(question.condition))
    elsif question.type.is_a?(BooleanType)
      BooleanQuestion.new(gui: self,
                          label: question.label,
                          id: question.variable.name,
                          condition: visit_calculation(question.condition))
    elsif question.type.kind_of?(MoneyType) || question.type.kind_of?(IntegerType)
      IntegerQuestion.new(gui: self,
                         label: question.label,
                         id: question.variable.name,
                         condition: visit_calculation(question.condition))
    elsif question.type.kind_of?(StringType)
      StringQuestion.new(gui: self,
                        label: question.label,
                        id: question.variable.name,
                        condition: visit_calculation(question.condition))
    end
  end

  # is it a question or an if statement?
  def visit_statement(statement, condition)
    if statement.kind_of?(Question)
      visit_question(statement, condition)
    elsif statement.kind_of?(IfStatement)
      visit_if_statement(statement, condition)
    else
      raise NotImplementedError
    end
  end

  #is the calculation a literal, a variable or an expression?
  def visit_calculation(calculation)
    return unless calculation
    if calculation.kind_of?(Variable)
      visit_variable(calculation)
    elsif calculation.kind_of?(Expression)
      visit_expression(calculation)
    else
      calculation
    end
  end

  # visit the calculations of both the left and right sides
  def visit_expression(subject)
    subject.left = visit_calculation(subject.left)
    subject.right = visit_calculation(subject.right)
    subject
  end

  # change variable to tk variable for gui
  def visit_variable(subject)
    @questions[subject.name].variable
  end
end