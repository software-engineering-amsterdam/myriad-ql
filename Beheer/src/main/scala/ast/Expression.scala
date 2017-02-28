package ast

import java.util.Date

import values._

import scala.language.postfixOps

sealed trait ExpressionNode

object ExpressionNode {
  type Env = Map[String, Value]

  def calculate(env: Env, expressionNode: ExpressionNode): Value = {
    expressionNode match {
      case Identifier(value) => env.getOrElse(value, UndefinedValue)
      case Mul(lhs, rhs) => calculate(env, lhs) * calculate(env, rhs)
      case Div(lhs, rhs) => calculate(env, lhs) / calculate(env, rhs)
      case Add(lhs, rhs) => calculate(env, lhs) + calculate(env, rhs)
      case Sub(lhs, rhs) => calculate(env, lhs) - calculate(env, rhs)
      case And(lhs, rhs) => calculate(env, lhs) && calculate(env, rhs)
      case Or(lhs, rhs) => calculate(env, lhs) || calculate(env, rhs)
      case Eq(lhs, rhs) => calculate(env, lhs) == calculate(env, rhs)
      case Neq(lhs, rhs) => calculate(env, lhs) != calculate(env, rhs)
      case Gt(lhs, rhs) => calculate(env, lhs) > calculate(env, rhs)
      case Lt(lhs, rhs) => calculate(env, lhs) < calculate(env, rhs)
      case Geq(lhs, rhs) => calculate(env, lhs) >= calculate(env, rhs)
      case Leq(lhs, rhs) => calculate(env, lhs) <= calculate(env, rhs)
      case Not(operand) => calculate(env, operand) !
      case Neg(operand) => calculate(env, operand) -
      case IntegerLiteral(value) => IntegerValue(value)
      case DecimalLiteral(value) => DecimalValue(value)
      case MoneyLiteral(value) => MoneyValue(value)
      case BooleanLiteral(value) => BooleanValue(value)
      case StringLiteral(value) => StringValue(value)
      case DateLiteral(value) => DateValue(value)
    }
  }
}

sealed trait InfixNode extends ExpressionNode {
  val lhs: ExpressionNode
  val rhs: ExpressionNode
}

sealed trait PrefixNode extends ExpressionNode {
  val operand: ExpressionNode
}

case class Identifier(value: String) extends ExpressionNode

case class Mul(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Div(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Add(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Sub(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class And(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Or(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Eq(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Neq(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Gt(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Lt(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Leq(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Geq(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixNode

case class Not(operand: ExpressionNode) extends PrefixNode

case class Neg(operand: ExpressionNode) extends PrefixNode

case class IntegerLiteral(value: BigDecimal) extends ExpressionNode

case class DecimalLiteral(value: BigDecimal) extends ExpressionNode

case class MoneyLiteral(value: BigDecimal) extends ExpressionNode

case class BooleanLiteral(value: Boolean) extends ExpressionNode

case class StringLiteral(value: String) extends ExpressionNode

case class DateLiteral(value: Date) extends ExpressionNode