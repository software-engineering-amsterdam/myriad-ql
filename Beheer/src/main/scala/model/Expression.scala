package model

sealed trait Expression {
  type Env = Map[String, Any]
}

sealed trait TypedExpression[T] extends Expression {
  def value(env: Env): T
}

sealed trait BooleanValue extends TypedExpression[Boolean]

sealed trait NumericValue extends TypedExpression[BigDecimal]

case class AND(lhs: BooleanValue, rhs: BooleanValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) && rhs.value(env)
}

case class OR(lhs: BooleanValue, rhs: BooleanValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) || rhs.value(env)
}

case class NOT(lhs: BooleanValue) extends BooleanValue {
  def value(env: Env): Boolean = !lhs.value(env)
}

case class LT(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) < rhs.value(env)
}

case class GT(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) > rhs.value(env)
}

case class EQ(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) == rhs.value(env)
}

case class LEQ(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) <= rhs.value(env)
}

case class GEQ(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) >= rhs.value(env)
}

case class NEQ(lhs: NumericValue, rhs: NumericValue) extends BooleanValue {
  def value(env: Env): Boolean = lhs.value(env) != rhs.value(env)
}

case class BooleanLiteral(value: Boolean) extends BooleanValue {
  def value(env: Env): Boolean = value
}

case class BooleanReference(value: String) extends BooleanValue {
  def value(env: Env): Boolean = env.get(value) match {
    case Some(v) => v match {
      case b: Boolean => b
      case e: BooleanValue => e.value(env)
      case _ => sys.error(s"Incorrect reference type for BooleanReference $value")
    }
    case None => sys.error(s"BooleanReference not defined in environment: $value")
  }
}

case class DIV(lhs: NumericValue, rhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = lhs.value(env) / rhs.value(env)
}

case class MUL(lhs: NumericValue, rhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = lhs.value(env) * rhs.value(env)
}

case class SUB(lhs: NumericValue, rhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = lhs.value(env) - rhs.value(env)
}

case class ADD(lhs: NumericValue, rhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = lhs.value(env) + rhs.value(env)
}

case class NEG(lhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = -lhs.value(env)
}

case class POS(lhs: NumericValue) extends NumericValue {
  def value(env: Env): BigDecimal = -lhs.value(env)
}

case class NumericReference(value: String) extends NumericValue {
  def value(env: Env): BigDecimal = env.get(value) match {
    case Some(v) => v match {
      case d: BigDecimal => d
      case e: NumericValue => e.value(env)
      case _ => sys.error(s"Incorrect reference type for NumericReference $value")
    }
    case None => sys.error(s"NumericReference not defined in environment: $value")
  }
}

case class IntLiteral(value: BigDecimal) extends NumericValue {
  def value(env: Env): BigDecimal = value
}

case class DecimalLiteral(value: BigDecimal) extends NumericValue {
  def value(env: Env): BigDecimal = value
}

case class MoneyLiteral(value: BigDecimal) extends NumericValue {
  def value(env: Env): BigDecimal = value
}