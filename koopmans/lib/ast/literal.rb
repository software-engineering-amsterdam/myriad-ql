require_relative '../helper'
require 'parslet'

class Literal
  extend Helper

  attr_reader :value

  def initialize(value)
    @value = value.to_s
  end
end

class BooleanLiteral < Literal
  def self.type
    'boolean'
  end
end

class IntegerLiteral < Literal
  def self.type
    'integer'
  end
end

class StringLiteral < Literal
  def self.type
    'string'
  end
end

class Parslet::Parser
  rule(:boolean_literal) do
    (str('true') | str('false')).as(:boolean) >> spaces?
  end

  rule(:integer_literal) do
    match('[0-9]').repeat(1).as(:integer) >> spaces?
  end

  rule(:string_literal) do
    str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces?
  end
end

class Parslet::Transform
  Literal.descendants.each do |literal|
    rule("#{literal.type}": simple(:value)) do
      literal.new(value)
    end
  end
end