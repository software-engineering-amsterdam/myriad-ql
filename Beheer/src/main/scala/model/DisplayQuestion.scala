package model

import ast.{ ExpressionNode, Type }
import model.DisplayQuestion.DisplayConditions

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayConditions: DisplayConditions
  val `type`: Type
}

case class DisplayCondition(condition: ExpressionNode, isElseCondition: Boolean)

case class OpenQuestion(identifier: String, label: String, `type`: Type, displayConditions: DisplayConditions) extends DisplayQuestion

case class ComputedQuestion(identifier: String, label: String, `type`: Type, displayConditions: DisplayConditions, value: ExpressionNode) extends DisplayQuestion

object DisplayQuestion {
  type DisplayQuestions = Seq[DisplayQuestion]
  type DisplayConditions = Seq[DisplayCondition]
}