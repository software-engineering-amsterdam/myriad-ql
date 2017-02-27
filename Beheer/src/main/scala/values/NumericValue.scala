package values

sealed trait NumericValue extends Value {
  val value: BigDecimal

  override def EQ(other: Value) = other match {
    case o: NumericValue => BooleanValue(value == o.value)
    case _ => UndefinedValue
  }

  override def NEQ(other: Value) = other match {
    case o: NumericValue => BooleanValue(value != o.value)
    case _ => UndefinedValue
  }

  override def GT(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value > o.value)
    case _ => UndefinedValue
  }

  override def LT(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value < o.value)
    case _ => UndefinedValue
  }

  override def GEQ(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value >= o.value)
    case _ => UndefinedValue
  }

  override def LEQ(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value <= o.value)
    case _ => UndefinedValue
  }
}

case class IntegerValue(value: BigDecimal) extends NumericValue {
  override def ADD(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value + o)
    case DecimalValue(o) => DecimalValue(value + o)
    case MoneyValue(o) => MoneyValue(value + o)
    case _ => UndefinedValue
  }

  override def SUB(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value - o)
    case DecimalValue(o) => DecimalValue(value - o)
    case MoneyValue(o) => MoneyValue(value - o)
    case _ => UndefinedValue
  }

  override def MUL(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value * o)
    case DecimalValue(o) => DecimalValue(value * o)
    case MoneyValue(o) => MoneyValue(value * o)
    case _ => UndefinedValue
  }

  override def DIV(other: Value): Value = other match {
    case o: NumericValue if o.value == 0 => UndefinedValue
    case IntegerValue(o) => IntegerValue(value / o)
    case DecimalValue(o) => DecimalValue(value / o)
    case MoneyValue(o) => MoneyValue(value / o)
    case _ => UndefinedValue
  }

  override def NEG = IntegerValue(-value)
}

case class DecimalValue(value: BigDecimal) extends NumericValue {
  override def ADD(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value + o)
    case o: NumericValue => DecimalValue(value + o.value)
    case _ => UndefinedValue
  }

  override def SUB(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value - o)
    case o: NumericValue => DecimalValue(value - o.value)
    case _ => UndefinedValue
  }

  override def MUL(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value * o)
    case o: NumericValue => DecimalValue(value * o.value)
    case _ => UndefinedValue
  }

  override def DIV(other: Value): Value = other match {
    case o: NumericValue if o.value == 0 => UndefinedValue
    case MoneyValue(o) => MoneyValue(value / o)
    case o: NumericValue => DecimalValue(value / o.value)
    case _ => UndefinedValue
  }

  override def NEG = DecimalValue(-value)
}

case class MoneyValue(value: BigDecimal) extends NumericValue {
  override def ADD(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value + o.value)
    case _ => UndefinedValue
  }

  override def SUB(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value - o.value)
    case _ => UndefinedValue
  }

  override def MUL(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value * o.value)
    case _ => UndefinedValue
  }

  override def DIV(other: Value): Value = other match {
    case o: NumericValue if o.value != 0 => MoneyValue(value / o.value)
    case _ => UndefinedValue
  }

  override def NEG = MoneyValue(-value)
}
