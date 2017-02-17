require 'tk'

class Question
  attr_accessor :gui
  attr_accessor :frame
  # attr_accessor :variable
  attr_accessor :hidden

  def initialize(gui, label)
    self.hidden = false
    gui = gui
    variable = TkVariable.new(true)

    self.frame = TkFrame.new {
      pack('side' => 'top', 'fill' => 'x')
    }

    TkLabel.new(frame) do
      text label
      pack
    end

    TkRadioButton.new(frame) do
      text 'Yes'
      variable variable
      value true
      pack
      command proc { gui.value_changed(self)}
    end

    TkRadioButton.new(frame) do
      text 'No'
      variable variable
      value false
      pack
      command proc { gui.value_changed(self) }
    end
  end

  def hide
    frame.pack_forget
    self.hidden = true
  end

  def show
    frame.pack
    self.hidden = false
  end

  def toggle
    if hidden
      show
    else
      hide
    end
  end
end