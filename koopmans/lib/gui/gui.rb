require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/variable'
require_relative '../ast/type'

require_relative 'question'
require_relative 'boolean_question'
require_relative 'text_question'
require_relative 'computed_question'

require 'tk'

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

  def initialize
    @questions = []
    q1 = BooleanQuestion.new(gui: self, label: 'hoe')
    q2 = BooleanQuestion.new(gui: self, label: 'wat')

    # condition = BooleanLiteral.new(true)
    condition1 = And.new(q2.variable, BooleanLiteral.new(true))
    condition = And.new(condition1, q1.variable)

    q3 = TextQuestion.new(gui: self, label: 'hoeveel', condition: condition)

    # expr = Add.new(IntegerLiteral.new(5), IntegerLiteral.new(5))
    calculation = Add.new(q3.variable, IntegerLiteral.new(5))

    ComputedQuestion.new(gui: self, label: 'hoeveel2', calculation: calculation, condition: condition)

    create_submit_button
    Tk.mainloop
  end

  def value_changed(question)
    # p 'value changed'
    # p question.value
    @questions.each(&:refresh)
    # @q4.refresh
    # p @q4
    # p 'value changed'
    # p question.value
    # if question.questions
    #   question.questions.each(&:refresh)
    # end
    # q4
  end

  def submit
    p @questions.select{ |q| !q.hidden}.map(&:to_json)
  end

  def create_submit_button
    button = TkButton.new.grid(row: @questions.size + 2)
    button.text = 'Submit'
    button.command = proc { submit }
  end


end

GUI.new

