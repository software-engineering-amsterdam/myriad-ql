package model

import ast.{ ExpressionNode, Type }

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayConditions: Seq[DisplayCondition]
  val `type`: Type
}

case class DisplayCondition(condition: ExpressionNode, isElseCondition: Boolean)

case class OpenQuestion(identifier: String, label: String, `type`: Type, displayConditions: Seq[DisplayCondition]) extends DisplayQuestion

case class ComputedQuestion(identifier: String, label: String, `type`: Type, displayConditions: Seq[DisplayCondition], value: ExpressionNode) extends DisplayQuestion