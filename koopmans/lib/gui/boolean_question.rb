require 'tk'

class BooleanQuestion < Question
  def initialize(args)
    super

    radio_button('Yes', true)
    radio_button('No', false)
  end

  def radio_button(text, value)
    radio_button = TkRadioButton.new(frame).pack
    radio_button.variable = self.variable
    radio_button.command = proc { gui.value_changed(self)}
    radio_button.text = text
    radio_button.value = value
  end
end