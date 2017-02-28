package values

import scala.math.BigDecimal.RoundingMode

sealed trait NumericValue extends Value {
  val value: BigDecimal

  override def ==(other: Value) = other match {
    case o: NumericValue => BooleanValue(value == o.value)
    case _ => UndefinedValue
  }

  override def !=(other: Value) = other match {
    case o: NumericValue => BooleanValue(value != o.value)
    case _ => UndefinedValue
  }

  override def >(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value > o.value)
    case _ => UndefinedValue
  }

  override def <(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value < o.value)
    case _ => UndefinedValue
  }

  override def >=(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value >= o.value)
    case _ => UndefinedValue
  }

  override def <=(other: Value): Value = other match {
    case o: NumericValue => BooleanValue(value <= o.value)
    case _ => UndefinedValue
  }
}

case class IntegerValue(value: BigDecimal) extends NumericValue {
  override def +(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value + o)
    case DecimalValue(o) => DecimalValue(value + o)
    case MoneyValue(o) => MoneyValue(value + o)
    case _ => UndefinedValue
  }

  override def !(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value - o)
    case DecimalValue(o) => DecimalValue(value - o)
    case MoneyValue(o) => MoneyValue(value - o)
    case _ => UndefinedValue
  }

  override def *(other: Value): Value = other match {
    case IntegerValue(o) => IntegerValue(value * o)
    case DecimalValue(o) => DecimalValue(value * o)
    case MoneyValue(o) => MoneyValue(value * o)
    case _ => UndefinedValue
  }

  override def /(other: Value): Value = other match {
    case o: NumericValue if o.value == 0 => UndefinedValue
    case IntegerValue(o) => IntegerValue(value / o)
    case DecimalValue(o) => DecimalValue(value / o)
    case MoneyValue(o) => MoneyValue(value / o)
    case _ => UndefinedValue
  }

  override def - = IntegerValue(-value)

  override def display = value.setScale(0, RoundingMode.HALF_EVEN).toString
}

case class DecimalValue(value: BigDecimal) extends NumericValue {
  override def +(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value + o)
    case o: NumericValue => DecimalValue(value + o.value)
    case _ => UndefinedValue
  }

  override def !(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value - o)
    case o: NumericValue => DecimalValue(value - o.value)
    case _ => UndefinedValue
  }

  override def *(other: Value): Value = other match {
    case MoneyValue(o) => MoneyValue(value * o)
    case o: NumericValue => DecimalValue(value * o.value)
    case _ => UndefinedValue
  }

  override def /(other: Value): Value = other match {
    case o: NumericValue if o.value == 0 => UndefinedValue
    case MoneyValue(o) => MoneyValue(value / o)
    case o: NumericValue => DecimalValue(value / o.value)
    case _ => UndefinedValue
  }

  override def - = DecimalValue(-value)

  override def display = value.setScale(2, RoundingMode.HALF_EVEN).toString
}

case class MoneyValue(value: BigDecimal) extends NumericValue {
  override def +(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value + o.value)
    case _ => UndefinedValue
  }

  override def !(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value - o.value)
    case _ => UndefinedValue
  }

  override def *(other: Value): Value = other match {
    case o: NumericValue => MoneyValue(value * o.value)
    case _ => UndefinedValue
  }

  override def /(other: Value): Value = other match {
    case o: NumericValue if o.value != 0 => MoneyValue(value / o.value)
    case _ => UndefinedValue
  }

  override def - = MoneyValue(-value)

  override def display = s"€${value.setScale(2, RoundingMode.HALF_EVEN)}"
}
