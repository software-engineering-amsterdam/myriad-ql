require_relative '../helper'
require 'parslet'

class Literal
  extend Helper

  attr_reader :value

  def initialize(value)
    @value = value.to_s
  end

  def accept(visitor)
    visitor.visit_literal(self)
  end
end

class BooleanLiteral < Literal
  def self.type
    'boolean'
  end

  def self.accept_types
    [BooleanType]
  end

  def eval
    return true if value == 'true'
    return false if value == 'false'
  end
end

class IntegerLiteral < Literal
  def self.type
    'integer'
  end

  def self.accept_types
    [IntegerType]
  end

  def eval
    value.to_i
  end
end

class StringLiteral < Literal
  def self.type
    'string'
  end

  def self.accept_types
    [StringType]
  end

  def eval
    value.to_s
  end
end