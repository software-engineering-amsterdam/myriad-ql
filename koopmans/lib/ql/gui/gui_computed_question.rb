class GUIComputedQuestion < GUIQuestion
  attr_accessor :calculation

  def initialize(args)
    super
    @calculation = args[:calculation]
    @variable.type = args[:type]
    create_computed_entry
    calculate
  end

  def reload
    super
    calculate
  end

  def calculate
    @variable.value = @calculation.eval if @calculation
  end

  def create_computed_entry
    entry = TkEntry.new(@frame).pack
    entry.textvariable = @variable
    entry.state = 'disabled'
  end
end