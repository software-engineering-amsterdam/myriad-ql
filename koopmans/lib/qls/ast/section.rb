class Section
  attr_reader :sections
  attr_reader :questions
  attr_reader :default_type_properties

  def initialize(sections, questions, default_type_properties)
    @sections = sections
    @questions = questions
    @default_type_properties = default_type_properties
  end
end