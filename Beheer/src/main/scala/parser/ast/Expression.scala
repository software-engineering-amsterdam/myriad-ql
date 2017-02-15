package parser.ast

import java.util.Date

sealed trait ExpressionNode

case class Identifier(value: String) extends ExpressionNode

case class InfixOperation(lhs: ExpressionNode, operator: String, rhs: ExpressionNode) extends ExpressionNode

case class PrefixOperation(operator: String, rhs: ExpressionNode) extends ExpressionNode

case class DecimalValue(value: BigDecimal) extends ExpressionNode

case class MoneyValue(value: BigDecimal) extends ExpressionNode

case class BooleanValue(value: Boolean) extends ExpressionNode

case class DateValue(value: Date) extends ExpressionNode