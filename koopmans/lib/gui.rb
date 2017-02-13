require 'tk'
class Gui

  def init
    @root = TkRoot.new { title 'Form' }
  end

  def new_question(label)
    TkLabel.new(@root) do
      text label
      pack { padx 15 ; pady 15; side 'left' }
    end
  end

  def launch
    Tk.mainloop
  end

end