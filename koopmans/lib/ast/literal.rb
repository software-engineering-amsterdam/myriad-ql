class Literal
  attr_reader :value

  def initialize(value)
    @value = value.to_s
  end

  def self.descendants
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end
end

class BooleanLiteral < Literal
  def self.to_type
    'boolean'
  end
end

class IntegerLiteral < Literal
  def self.to_type
    'integer'
  end
end

class StringLiteral < Literal
  def self.to_type
    'string'
  end
end

