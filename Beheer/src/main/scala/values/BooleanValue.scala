package values

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
