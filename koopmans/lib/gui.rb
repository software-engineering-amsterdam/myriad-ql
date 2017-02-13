require 'tk'
class Gui
  def question(label)
    answer =  TkVariable.new (true);

    TkLabel.new {
      text label
      pack('side' => 'top', 'fill' => 'x')
    }

    TkRadioButton.new do
      text 'Yes'
      variable answer
      value true
      pack('side' => 'top', 'fill' => 'x')
      command proc {p answer}
    end

    TkRadioButton.new do
      text 'No'
      variable answer
      value false
      pack('side' => 'top', 'fill' => 'x')
      command proc {p answer}
    end

  end

  def launch
    Tk.mainloop
  end

end