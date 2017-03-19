package model

import ast.{ ExpressionNode, Type }

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayCondition: Seq[ExpressionNode]
  val `type`: Type
}

case class OpenQuestion(identifier: String, label: String, `type`: Type, displayCondition: Seq[ExpressionNode]) extends DisplayQuestion

case class ComputedQuestion(identifier: String, label: String, `type`: Type, displayCondition: Seq[ExpressionNode], value: ExpressionNode) extends DisplayQuestion