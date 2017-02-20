require 'tk'

class TextQuestion < Question
  attr_accessor :previous_value

  def initialize(args)
    super
    @previous_value = value
    @variable.value = (0)
    create_entry
  end

  def create_entry
    entry = TkEntry.new(frame).pack
    entry.textvariable = self.variable
    # every time enter is pressed
    entry.bind('Return') do
      # only if value changes
      return if @previous_value == value
      gui.value_changed(self)
      @previous_value = value
    end
  end
end