require_relative '../helper'
require 'parslet'

class Type
  extend Helper

  # def accept(type_checker)
  #   type_checker.visit_type(self)
  # end
end

class BooleanType < Type
  def self.type
    'boolean'
  end

  def self.accept_types
    [BooleanType]
  end
end

class IntegerType < Type
  def self.type
    'integer'
  end

  def self.accept_types
    [IntegerType, MoneyType]
  end
end

class DateType < Type
  def self.type
    'date'
  end

  def self.accept_types
    [DateType]
  end
end

class DecimalType < Type
  def self.type
    'decimal'
  end

  def self.accept_types
    [DecimalType]
  end
end

class StringType < Type
  def self.type
    'string'
  end

  def self.accept_types
    [StringType]
  end
end

# TODO make money type allow +-/* operations
class MoneyType < Type
  def self.type
    'money'
  end

  def self.accept_types
    [MoneyType, IntegerType]
  end
end


class Parser < Parslet::Parser
  rule(:type) do
    Type.descendants.map { |type| str(type.type) }.reduce(&:|).as(:type) >> spaces?
  end
end