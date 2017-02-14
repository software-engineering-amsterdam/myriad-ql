class Literal
  attr_reader :value

  def initialize(value)
    @value = value
  end
end

class BooleanLiteral < Literal
end

class IntegerLiteral < Literal
end

class StringLiteral < Literal
end

