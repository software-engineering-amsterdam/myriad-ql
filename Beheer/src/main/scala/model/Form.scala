package model

import parser.ast.{ ExpressionNode, Type }

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayCondition: Iterable[ExpressionNode]
  val `type`: Type

  def show(env: Map[String, Value]): Boolean = displayCondition.map(_.value(env) match { case BooleanValue(b) => b }).reduce(_ && _)
}

case class OpenQuestion(identifier: String, label: String, `type`: Type, displayCondition: Iterable[ExpressionNode]) extends DisplayQuestion

case class ComputedQuestion(identifier: String, label: String, `type`: Type, displayCondition: Iterable[ExpressionNode], value: ExpressionNode) extends DisplayQuestion
