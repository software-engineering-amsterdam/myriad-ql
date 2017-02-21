class TkVariable
  attr_accessor :type

  def eval
    case type
      when BooleanType
        bool
      when StringType
        string
      when (IntegerType or MoneyType)
        numeric
      else
        value
    end
  end
end
