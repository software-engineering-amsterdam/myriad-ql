class ComputedQuestion < Question
  attr_accessor :calculation

  def initialize(args)
    super
    @calculation = args[:calculation]
    @variable.value = @calculation.eval

    create_computed_entry
  end

  def refresh
    super
    if @calculation
      @variable.value = @calculation.eval
    end
  end

  def create_computed_entry
    entry = TkEntry.new(frame).pack
    entry.textvariable = self.variable
    entry.state = 'disabled'
  end
end