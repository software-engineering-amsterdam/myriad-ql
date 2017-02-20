package parser.ast

import java.util.Date

sealed trait ExpressionNode

case class Identifier(value: String) extends ExpressionNode

case class InfixOperation(lhs: ExpressionNode, operator: String, rhs: ExpressionNode) extends ExpressionNode

case class PrefixOperation(operator: String, rhs: ExpressionNode) extends ExpressionNode

case class IntegerLiteral(value: BigDecimal) extends ExpressionNode

case class DecimalLiteral(value: BigDecimal) extends ExpressionNode

case class MoneyLiteral(value: BigDecimal) extends ExpressionNode

case class BooleanLiteral(value: Boolean) extends ExpressionNode

case class StringLiteral(value: String) extends ExpressionNode

case class DateLiteral(value: Date) extends ExpressionNode