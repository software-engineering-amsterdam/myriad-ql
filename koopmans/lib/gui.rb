require 'tk'
# class Gui
#
#     #
#     # TkLabel.new {
#     #   textvariable answer
#     #   pack('side' => 'top', 'fill' => 'x')
#     # }
#
#     def launch
#       Tk.mainloop
#     end
#
#     def question(label)
#       frame = TkFrame.new {
#         pack('side' => 'left')
#       }
#
#       answer =  TkVariable.new (true);
#
#       TkLabel.new(frame) {
#         text label
#         pack
#       }
#
#       TkRadioButton.new(frame) do
#         text 'Yes'
#         variable answer
#         value true
#         pack
#         # command proc {@answer['textvariable'].value = answer.value}
#       end
#
#       x = TkRadioButton.new(frame) do
#         text 'No'
#         variable answer
#         value false
#         pack
#         # command proc {answer['textvariable'].value = answer.value}
#       end
#
#       frame.pack_forget
#       # frame.pack
#     end
# end

class GUIQuestion
  attr_accessor :frame
  attr_accessor :variable
  attr_accessor :hidden

  def initialize(label, variable, hidden = false)
    self.frame = TkFrame.new {
      pack('side' => 'top', 'fill' => 'x')
    }

    # variable =  TkVariable.new (true);

    TkLabel.new(frame) do
      text label
      pack
    end

    TkRadioButton.new(frame) do
      text 'Yes'
      variable variable
      value true
      pack
      # command proc {@answer['textvariable'].value = answer.value}
    end

    TkRadioButton.new(frame) do
      text 'No'
      variable variable
      value false
      pack
      # command proc {answer['textvariable'].value = answer.value}
    end

    # frame.pack_forget
  end

  def toggle
    if hidden
      self.frame.pack
      self.hidden = false
    else
      self.frame.pack_forget
      self.hidden = true
    end
  end
end

class GUIIf
  attr_accessor :condition
  attr_accessor :questions

  def initialize(condition, questions)
    condition = condition
    questions = questions
  end
end

v = TkVariable.new(true)
v2 = TkVariable.new(true)
q1 = GUIQuestion.new('hola', v)
# q2 = GUIQuestion.new('hola2', v)
q3 = GUIQuestion.new('hola3', v2)

ifs = GUIIf.new(v, [q3])

# q1.toggle
# q1.toggle


Tk.mainloop