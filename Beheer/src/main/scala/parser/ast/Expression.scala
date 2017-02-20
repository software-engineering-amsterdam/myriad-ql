package parser.ast

import java.util.Date

sealed trait ExpressionNode {
  type Env = Map[String, Any]
}

sealed trait TypedExpression[T] extends ExpressionNode {
  def value(env: Env): T
}

sealed trait BooleanValue extends TypedExpression[Boolean]

sealed trait NumericValue extends TypedExpression[BigDecimal]

case class Identifier(value: String) extends ExpressionNode

sealed trait InfixOperation extends ExpressionNode

case class MUL(lhs: NumericValue, rhs: NumericValue) extends InfixOperation

case class DIV(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class ADD(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class SUB(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class AND(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class OR(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class EQ(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class NEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class GT(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class LT(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class LEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class GEQ(lhs: ExpressionNode, rhs: ExpressionNode) extends InfixOperation

case class PrefixOperation(operator: String, rhs: ExpressionNode) extends ExpressionNode

case class IntegerLiteral(value: BigDecimal) extends ExpressionNode

case class DecimalLiteral(value: BigDecimal) extends ExpressionNode

case class MoneyLiteral(value: BigDecimal) extends ExpressionNode

case class BooleanLiteral(value: Boolean) extends ExpressionNode

case class StringLiteral(value: String) extends ExpressionNode

case class DateLiteral(value: Date) extends ExpressionNode