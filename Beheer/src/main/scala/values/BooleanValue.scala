package values

case class BooleanValue(value: Boolean) extends Value {
  override def &&(other: Value) = other match {
    case BooleanValue(o) => BooleanValue(value && o)
    case _ => UndefinedValue
  }

  override def ||(other: Value) = other match {
    case BooleanValue(o) => BooleanValue(value || o)
    case _ => UndefinedValue
  }

  override def ! = BooleanValue(!value)

  override def display = value.toString
}
