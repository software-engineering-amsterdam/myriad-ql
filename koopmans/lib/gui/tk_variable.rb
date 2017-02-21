class TkVariable
  attr_accessor :type

  def eval
    if type == BooleanType
      bool
    elsif type == StringType
      string
    elsif type == IntegerType || type == MoneyType
      numeric
    else
      value
    end
  end
end
