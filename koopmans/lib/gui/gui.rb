require_relative 'question'
require 'tk'

class GUI
  attr_accessor :questions

  def initialize
    @questions = []
    add_question('How is the weather')
    add_question('How are you')

    Tk.mainloop
  end

  def add_question(label)
    @questions << Question.new(self, label)
  end

  def value_changed(question)
    p question.variable.value

    p @questions[1].toggle
  end
end

GUI.new

