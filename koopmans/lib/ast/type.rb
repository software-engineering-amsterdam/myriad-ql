class Type
  def self.descendants
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end
end

class BooleanType < Type
  def self.to_type
    'boolean'
  end
end

class IntegerType < Type
  def self.to_type
    'integer'
  end
end

class DateType < Type
  def self.to_type
    'date'
  end
end

class DecimalType < Type
  def self.to_type
    'decimal'
  end
end

class StringType < Type
  def self.to_type
    'string'
  end
end

class MoneyType < Type
  def self.to_type
    'money'
  end
end