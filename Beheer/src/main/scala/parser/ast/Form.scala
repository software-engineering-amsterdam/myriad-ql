package parser.ast

sealed trait FormNode

case class Form(identifier: String, block: Block) extends FormNode

case class Block(statements: Seq[Statement]) extends FormNode

sealed trait Statement extends FormNode

case class Conditional(condition: ExpressionNode, block: Block) extends Statement

case class Question(identifier: String, label: String, `type`: Type, expressionNode: Option[ExpressionNode] = None) extends Statement

sealed trait Type extends FormNode

case object Boolean extends Type

case object String extends Type

case object Date extends Type

sealed trait NumericType extends Type

case object Integer extends NumericType

case object Decimal extends NumericType

case object Money extends NumericType

