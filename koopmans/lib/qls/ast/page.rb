class Page
  attr_reader :sections
  attr_reader :default_type_properties

  def initialize(sections, default_type_properties)
    @sections = sections
    @default_type_properties = default_type_properties
  end
end