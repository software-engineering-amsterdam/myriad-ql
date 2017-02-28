package values

case object UndefinedValue extends Value {
  override def ==(other: Value) = UndefinedValue

  override def !=(other: Value) = UndefinedValue

  override def display = "Value not defined"
}
