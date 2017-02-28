package values

trait Value {
  def ==(other: Value): Value = BooleanValue(this.equals(other))

  def !=(other: Value): Value = BooleanValue(!this.equals(other))

  def +(other: Value): Value = UndefinedValue

  def !(other: Value): Value = UndefinedValue

  def *(other: Value): Value = UndefinedValue

  def /(other: Value): Value = UndefinedValue

  def >(other: Value): Value = UndefinedValue

  def <(other: Value): Value = UndefinedValue

  def >=(other: Value): Value = UndefinedValue

  def <=(other: Value): Value = UndefinedValue

  def &&(other: Value): Value = UndefinedValue

  def ||(other: Value): Value = UndefinedValue

  def Not: Value = UndefinedValue

  def Neg: Value = UndefinedValue

  def ! = Not

  def - = Neg

  def display: String
}
