package parser.ast

sealed trait FormNode

case class Form(identifier: String, block: Block) extends FormNode

case class Block(statements: Seq[Statement]) extends FormNode

sealed trait Statement extends FormNode

case class Conditional(condition: ExpressionNode, block: Block) extends Statement

case class Question(identifier: String, label: String, `type`: Type, expressionNode: Option[ExpressionNode] = None) extends Statement

case class Type(typeName: String) extends FormNode

