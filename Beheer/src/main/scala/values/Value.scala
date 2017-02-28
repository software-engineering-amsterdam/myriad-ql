package values

trait Value {
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

  def display: String
}

