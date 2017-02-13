package parser.ast

sealed trait ExpressionNode

case class Value(value: Int) extends ExpressionNode

case class Identifier(value: String) extends ExpressionNode

case class InfixOperation(lhs: ExpressionNode, operator: String, rhs: ExpressionNode) extends ExpressionNode

case class PrefixOperation(operator: String, rhs: ExpressionNode) extends ExpressionNode
