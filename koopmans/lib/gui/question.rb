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
    refresh
  end

  def create_frame
    @frame = TkFrame.new.grid(row: @gui.questions.size)
  end

  def value
    @variable.value
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

  def refresh
    if @condition
      @condition.eval ? show : hide
    end
  end

  def create_label
    label = TkLabel.new(frame).pack
    label.text = @label
  end
end