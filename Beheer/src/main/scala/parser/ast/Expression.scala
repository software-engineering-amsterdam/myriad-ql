package parser.ast

import java.util.Date

import model._

sealed trait ExpressionNode {
  type Env = Map[String, Value]

  def value(env: Env): Value
}

sealed trait InfixNode extends ExpressionNode {
  val lhs: ExpressionNode
  val rhs: ExpressionNode
}

sealed trait PrefixNode extends ExpressionNode {
  val rhs: ExpressionNode
}

sealed trait Comparison extends InfixNode
sealed trait Relation extends InfixNode
sealed trait Logic extends InfixNode
sealed trait Arithmetic extends InfixNode

case class Identifier(value: String) extends ExpressionNode {
  def value(env: Env): Value = env.getOrElse(value, UndefinedValue)
}

case class MUL(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env).MUL(rhs.value(env))
}

case class DIV(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env).DIV(rhs.value(env))
}

case class ADD(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env).ADD(rhs.value(env))
}

case class SUB(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env).SUB(rhs.value(env))
}

case class AND(lhs: ExpressionNode, rhs: ExpressionNode) extends Logic {
  def value(env: Env): Value = lhs.value(env).AND(rhs.value(env))
}

case class OR(lhs: ExpressionNode, rhs: ExpressionNode) extends Logic {
  def value(env: Env): Value = lhs.value(env).OR(rhs.value(env))
}

case class EQ(lhs: ExpressionNode, rhs: ExpressionNode) extends Comparison {
  def value(env: Env): Value = lhs.value(env).EQ(rhs.value(env))
}

case class NEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends Comparison {
  def value(env: Env): Value = lhs.value(env).NEQ(rhs.value(env))
}

case class GT(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env).GT(rhs.value(env))
}

case class LT(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env).LT(rhs.value(env))
}

case class LEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env).LEQ(rhs.value(env))
}

case class GEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env).GEQ(rhs.value(env))
}

case class NOT(rhs: ExpressionNode) extends PrefixNode {
  def value(env: Env): Value = rhs.value(env).NOT
}

case class NEG(rhs: ExpressionNode) extends PrefixNode {
  def value(env: Env): Value = rhs.value(env).NEG
}

case class IntegerLiteral(value: BigDecimal) extends ExpressionNode {
  def value(env: Env): Value = IntegerValue(value)
}

case class DecimalLiteral(value: BigDecimal) extends ExpressionNode {
  def value(env: Env): Value = DecimalValue(value)
}

case class MoneyLiteral(value: BigDecimal) extends ExpressionNode {
  def value(env: Env): Value = MoneyValue(value)
}

case class BooleanLiteral(value: Boolean) extends ExpressionNode {
  def value(env: Env): Value = BooleanValue(value)
}

case class StringLiteral(value: String) extends ExpressionNode {
  def value(env: Env): Value = StringValue(value)
}

case class DateLiteral(value: Date) extends ExpressionNode {
  def value(env: Env): Value = DateValue(value)
}