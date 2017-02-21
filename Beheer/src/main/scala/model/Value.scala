package model

import java.util.Date

sealed trait Value {
  def EQ(other: Value): Value = BooleanValue(this == other)

  def NEQ(other: Value): Value = BooleanValue(this != other)

  def ADD(other: Value): Value = UndefinedValue

  def SUB(other: Value): Value = UndefinedValue

  def MUL(other: Value): Value = UndefinedValue

  def DIV(other: Value): Value = UndefinedValue

  def GT(other: Value): Value = UndefinedValue

  def LT(other: Value): Value = UndefinedValue

  def GEQ(other: Value): Value = UndefinedValue

  def LEQ(other: Value): Value = UndefinedValue

  def AND(other: Value): Value = UndefinedValue

  def OR(other: Value): Value = UndefinedValue

  def NEG: Value = UndefinedValue

  def NOT: Value = UndefinedValue
}

case object UndefinedValue extends Value {
  override def EQ(other: Value) = UndefinedValue

  override def NEQ(other: Value) = UndefinedValue
}

case class BooleanValue(value: Boolean) extends Value {
  override def AND(other: Value) = other match {
    case BooleanValue(o) => BooleanValue(value && o)
    case _ => UndefinedValue
  }

  override def OR(other: Value) = other match {
    case BooleanValue(o) => BooleanValue(value || o)
    case _ => UndefinedValue
  }

  override def NOT = BooleanValue(!value)
}

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

case class StringValue(value: String) extends Value {
  /*override def ADD(other: Value) = other match {
    case StringValue(o) => StringValue(value + o)
    case _ => UndefinedValue
  }*/
}

case class DateValue(value: Date) extends Value