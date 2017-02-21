require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/variable'
require_relative '../ast/type'

require_relative 'question'
require_relative 'boolean_question'
require_relative 'text_question'
require_relative 'computed_question'
require_relative 'question_visitor'

require 'tk'
require 'pp'

class QuestionVisitor
  attr_accessor :questions
  attr_accessor :gui

  def initialize(gui)
    @gui = gui
    @questions = Hash.new
  end

  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    subject.statements.map { |statement| visit_statement(statement, nil) }.flatten
    @questions
  end

  # visit all statements of the if block
  def visit_if_statement(subject, condition)
    if condition
      condition = And.new(condition, subject.expression)
    else
      condition = subject.expression
    end
    # {
    #     if: {
    #         condition: subject.expression,
    #         statements: subject.block.map { |statement| visit_statement(statement) }
    #     }
    # }
    # ["#{subject.expression}": subject.block.map { |statement| visit_statement(statement) }]

    subject.block.map { |statement| visit_statement(statement, condition) }
  end

  # return question
  def visit_question(subject, condition)
    subject.condition = condition
    subject
    # p subject.type
    if subject.type.kind_of? BooleanType
      # p 'joe'
      # , id: subject.variable.name)
      @questions[subject.variable.name] = BooleanQuestion.new(gui: @gui, label: subject.label)
      # return [subject.variable.name, BooleanQuestion.new(label: subject.label)]
    end

    if (subject.type.kind_of? MoneyType) && subject.assignment
      # p subject.assignments
      @questions[subject.variable.name] = ComputedQuestion.new(gui: @gui, label: subject.label, calculation: visit_calculation(subject.assignment))
    elsif (subject.type.kind_of? MoneyType) && subject.condition
      @questions[subject.variable.name] = TextQuestion.new(gui: @gui, label: subject.label, condition: visit_calculation(subject.condition))
    end
    # nil

    # { question: subject }
    #
    # if condition
    #   {question: subject, condition: condition}
    #   # subject.condition = condition
    # else
    #   {question: subject}
    # end

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

    # # is the calculation a literal, a variable or an expression?
    def visit_calculation(calculation)
      if calculation.kind_of?(Literal)
        visit_literal(calculation)
      elsif calculation.kind_of?(Variable)
        visit_variable(calculation)
      elsif calculation.kind_of?(Expression)
        visit_expression(calculation)
      else
        raise NotImplementedError
      end
    end

    # literal should return nil
    def visit_literal(subject)
      subject
    end

    # visit the calculations of both the left and right sides
    def visit_expression(subject)
      # p subject.left
      subject.left = visit_calculation(subject.left)
      subject.right = visit_calculation(subject.right)
      subject
      # [visit_calculation(subject.left), visit_calculation(subject.right)]
    end

    def visit_variable(subject)
      @questions[subject.name].variable
    end
end