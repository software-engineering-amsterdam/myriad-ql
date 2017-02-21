package model.values

case object UndefinedValue extends Value {
  override def EQ(other: Value) = UndefinedValue

  override def NEQ(other: Value) = UndefinedValue
}
