package values

trait Value {
  def ==(other: Value): Value = BooleanValue(this.equals(other))

  def !=(other: Value): Value = BooleanValue(!this.equals(other))

  def +(other: Value): Value = UndefinedValue

  def -(other: Value): Value = UndefinedValue

  def *(other: Value): Value = UndefinedValue

  def /(other: Value): Value = UndefinedValue

  def >(other: Value): Value = UndefinedValue

  def <(other: Value): Value = UndefinedValue

  def >=(other: Value): Value = UndefinedValue

  def <=(other: Value): Value = UndefinedValue

  def &&(other: Value): Value = UndefinedValue

  def ||(other: Value): Value = UndefinedValue

  def ! = Not

  private def Not: Value = UndefinedValue

  def - = Neg

  private def Neg: Value = UndefinedValue

  def display: String
}
