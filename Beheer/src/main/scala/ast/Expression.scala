package ast

import java.util.Date

import ast.ExpressionNode.Env
import values._

import scala.language.postfixOps

sealed trait ExpressionNode {
  def value(env: Env): Value
}

object ExpressionNode {
  type Env = Map[String, Value]
}

sealed trait InfixNode extends ExpressionNode {
  val lhs: ExpressionNode
  val rhs: ExpressionNode
}

sealed trait PrefixNode extends ExpressionNode {
  val operand: ExpressionNode
}

sealed trait Comparison extends InfixNode

sealed trait Relation extends InfixNode

sealed trait Logic extends InfixNode

sealed trait Arithmetic extends InfixNode

case class Identifier(value: String) extends ExpressionNode {
  def value(env: Env): Value = env.getOrElse(value, UndefinedValue)
}

case class Mul(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env) * rhs.value(env)
}

case class Div(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env) / rhs.value(env)
}

case class Add(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env) + rhs.value(env)
}

case class Sub(lhs: ExpressionNode, rhs: ExpressionNode) extends Arithmetic {
  def value(env: Env): Value = lhs.value(env) ! rhs.value(env)
}

case class And(lhs: ExpressionNode, rhs: ExpressionNode) extends Logic {
  def value(env: Env): Value = lhs.value(env) && rhs.value(env)
}

case class Or(lhs: ExpressionNode, rhs: ExpressionNode) extends Logic {
  def value(env: Env): Value = lhs.value(env) || rhs.value(env)
}

case class Eq(lhs: ExpressionNode, rhs: ExpressionNode) extends Comparison {
  def value(env: Env): Value = lhs.value(env) == rhs.value(env)
}

case class Neq(lhs: ExpressionNode, rhs: ExpressionNode) extends Comparison {
  def value(env: Env): Value = lhs.value(env) != rhs.value(env)
}

case class Gt(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env) > rhs.value(env)
}

case class Lt(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env) < rhs.value(env)
}

case class Leq(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env) <= rhs.value(env)
}

case class Geq(lhs: ExpressionNode, rhs: ExpressionNode) extends Relation {
  def value(env: Env): Value = lhs.value(env) >= rhs.value(env)
}

case class Not(operand: ExpressionNode) extends PrefixNode {
  def value(env: Env): Value = operand.value(env) !
}

case class Neg(operand: ExpressionNode) extends PrefixNode {
  def value(env: Env): Value = operand.value(env) -
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