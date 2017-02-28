package values

case object UndefinedValue extends Value {
  override def EQ(other: Value) = UndefinedValue

  override def NEQ(other: Value) = UndefinedValue

  override def display = "Value not defined"
}
