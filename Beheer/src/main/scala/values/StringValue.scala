package values

case class StringValue(value: String) extends Value {
  /*override def ADD(other: Value) = other match {
    case StringValue(o) => StringValue(value + o)
    case _ => UndefinedValue
  }*/
}
