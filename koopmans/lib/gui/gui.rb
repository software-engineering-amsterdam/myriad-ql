require_relative 'question'
require_relative 'boolean_question'
require_relative 'text_question'

require 'tk'

class GUI
  attr_accessor :questions

  def initialize
    @questions = []
    q1 = BooleanQuestion.new(gui: self, label: 'hoe')
    q2 = BooleanQuestion.new(gui: self, label: 'wat')
    q1.questions = [q2]
    q3 = TextQuestion.new(gui: self, label: 'hoeveel')
    create_submit_button
    Tk.mainloop
  end

  def value_changed(question)
    p 'value changed'
    p question.value
    if question.questions
      question.questions.each(&:toggle)
    end
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

