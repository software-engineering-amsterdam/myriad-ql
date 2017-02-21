require 'tk'
require 'pp'
require 'require_all'

require_rel '../ast'
require_rel '/'

class GUI < GUIQuestionVisitor
  def initialize(ast)
    super

    create_submit_button
    Tk.mainloop
  end

  def value_changed
    @questions.each_value(&:reload)
  end

  def submit
    p @questions.each_value.select{ |question| question.enabled }.map(&:to_json)
  end

  def create_submit_button
    button = TkButton.new.grid(row: @questions.size + 1)
    button.text = 'Submit'
    button.command = proc { submit }
  end
end