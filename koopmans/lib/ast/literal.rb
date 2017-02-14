class Literal
  attr_reader :value

  def initialize(value)
    @value = value.to_s
  end
end

class BooleanLiteral < Literal
end

class IntegerLiteral < Literal
end

class StringLiteral < Literal
end

