package values

case class StringValue(value: String) extends Value {

  override def display = value
}
