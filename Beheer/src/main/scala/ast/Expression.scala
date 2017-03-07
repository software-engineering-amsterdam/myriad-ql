package ast

import java.util.Date

import scala.language.postfixOps

sealed trait ExpressionNode

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