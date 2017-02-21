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

class TkVariable
  attr_accessor :type

  def eval
    if type == BooleanType
      bool
    elsif type == StringType
      string
    elsif type == IntegerType
      numeric
    else
      value
    end
  end
end

class GUI
  attr_accessor :questions

  def initialize(ast)
    # @questions = Hash.new
    @questions  = ast.accept(QuestionVisitor.new(self))

    pp @questions
    # pp ast
    # ast_questions = ast.accept(QuestionVisitor.new)
    #
    # ast_questions.each do |q|
    #   if q.type.kind_of? BooleanType
    #     BooleanQuestion.new(gui: self, label: q.label, id: q.variable.name)
    #   end
    #   if q.type.kind_of? MoneyType
    #     p q.condition
    #     TextQuestion.new(gui: self, label: q.label, id: q.variable.name)
    #   end
    # end



    # BooleanQuestion.new(gui: self, label: 'hoe', id: 'hoe')
    # BooleanQuestion.new(gui: self, label: 'wat', id: 'wat')
    #
    # # condition = BooleanLiteral.new(true)
    # condition1 = And.new(@questions['hoe'].variable, BooleanLiteral.new(true))
    # condition = And.new(condition1, @questions['wat'].variable)
    #
    # TextQuestion.new(gui: self, label: 'hoeveel', condition: condition, id: 'hoeveel1')
    # TextQuestion.new(gui: self, label: 'hoeveel2', condition: condition, id: 'hoeveel2')
    #
    #
    # calculation1 = Add.new(@questions['hoeveel1'].variable, IntegerLiteral.new(5))
    # calculation = Add.new(calculation1, @questions['hoeveel2'].variable)
    #
    # ComputedQuestion.new(gui: self, label: 'hoeveel3', id: 'hoeveel3', calculation: calculation, condition: condition)
    # p @questions[0].each do |q|
    #   p q
    # end

    # @questions.each {|key, value| puts "#{key} is #{value}" }

    create_submit_button
    Tk.mainloop
  end

  def value_changed(question)
    # @questions.each {|key, value| value.refresh }
    @questions.each_value(&:refresh)
  end

  def submit
    # p @questions.each_value.
    p @questions.each_value.select{ |q| !q.hidden}.map(&:to_json)
  end

  def create_submit_button
    button = TkButton.new.grid(row: @questions.size + 2)
    button.text = 'Submit'
    button.command = proc { submit }
  end
end

# GUI.new

