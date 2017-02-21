require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/variable'
require_relative '../ast/type'

require_relative 'gui_question'
require_relative 'text_question'
require_relative 'boolean_question'
require_relative 'string_question'
require_relative 'computed_question'
require_relative 'gui_question_visitor'
require_relative 'tk_variable'

require 'tk'
require 'pp'

class GUI < GUIQuestionVisitor
  def check(typechecker)
    if !typechecker[:errors].empty?
      Tk.messageBox(
        type: 'ok',
        icon: 'error',
        title: 'Errors found!',
        message: typechecker[:errors].join('\n')
      )
      return 'quit'
    elsif !typechecker[:warnings].empty?
      Tk.messageBox(
          type: 'ok',
          icon: 'warning',
          title: 'Warnings found!',
          message: typechecker[:warnings].join('\n')
      )
      return 'continue'
    end
  end

  def initialize(ast, typechecker)
    return if check(typechecker) == 'quit'
    super

    create_submit_button
    Tk.mainloop
  end

  def value_changed
    @questions.each_value(&:reload)
  end

  def submit
    p @questions.each_value.select { |question| question.enabled }.map(&:to_json)
  end

  def create_submit_button
    button = TkButton.new.grid(row: @questions.size + 1)
    button.text = 'Submit'
    button.command = proc { submit }
  end
end

