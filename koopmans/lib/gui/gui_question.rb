class GUIQuestion
  attr_accessor :gui
  attr_accessor :label
  attr_accessor :frame
  attr_accessor :hidden
  attr_accessor :variable
  attr_accessor :condition

  def initialize(args)
    @gui = args[:gui]
    @label = args[:label]
    @condition = args[:condition]

    @hidden = false
    @variable = TkVariable.new()
    @gui.questions[args[:id]] = self

    create_frame
    create_label
    check_condition
  end

  def create_frame
    @frame = TkFrame.new.grid(row: @gui.questions.size)
  end

  def value
    @variable.eval
  end

  def to_json
    {@label => value}
  end

  def hide
    @frame.grid_remove
    @hidden = true
  end

  def show
    @frame.grid
    @hidden = false
  end

  def reload
    check_condition
  end

  def check_condition
    @condition.eval ? show : hide if @condition
  end

  def create_label
    label = TkLabel.new(frame).pack
    label.text = @label
  end
end