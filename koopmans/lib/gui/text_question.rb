require 'tk'

class TextQuestion < Question
  attr_accessor :previous_value

  def initialize(args)
    super
    @previous_value = value
    entry
  end

  def entry
    entry = TkEntry.new(frame).pack
    entry.textvariable = self.variable
    # every time enter is pressed
    entry.bind('Return') do
      # only if value changes
      unless @previous_value == value
        gui.value_changed(self)
        @previous_value = value
      end
    end
  end
end