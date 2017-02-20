require 'tk'

class Question
  attr_accessor :gui
  attr_accessor :label
  attr_accessor :frame
  attr_accessor :hidden
  attr_accessor :questions
  attr_accessor :variable
  attr_accessor :condition

  def initialize(args)
    @questions = args[:questions]
    @hidden = false
    @gui = args[:gui]
    @gui.questions << self
    @variable = TkVariable.new()
    @frame = TkFrame.new.grid(row: @gui.questions.size)
    @label = args[:label]
    @condition = args[:condition]

    create_label
    refresh
  end

  def value
    variable.value
  end

  def to_json
    {@label => value}
  end

  def hide
    frame.grid_remove
    @hidden = true
  end

  def show
    frame.grid
    @hidden = false
  end

  def refresh
    if @condition
      p @condition.eval
      @condition.eval ? show : hide
    end
  end

  def create_label
    label = TkLabel.new(frame).pack
    label.text = @label
  end
end