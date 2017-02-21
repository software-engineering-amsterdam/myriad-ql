class StringQuestion < TextQuestion

  def initialize(args)
    super
    @variable.value = ("")
    @variable.type = StringType
    @previous_value = value
  end
end