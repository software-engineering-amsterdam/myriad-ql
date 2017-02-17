require 'parslet'

class Form
  attr_reader :variable
  attr_reader :statements

  def initialize(variable, statements)
    @variable = variable
    @statements = statements
  end

  # go through each element and 'visit' it
  def accept(visitor)
    @statements.each do |element|
      element.accept(visitor)
    end
  end
end
module FormParser
  include Parslet

  rule(:form) do
    spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form)
  end
end