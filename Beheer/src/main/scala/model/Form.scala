package model

import parser.ast.{ ExpressionNode, Type }

case class Form(questions: Seq[Question])

sealed trait Question {
  val identifier: String
  val label: String
  val show: Iterable[BooleanValue]
  val `type`: Type
}

case class OpenQuestion(identifier: String, label: String, `type`: Type, show: Iterable[BooleanValue]) extends Question

case class ComputedQuestion(identifier: String, label: String, `type`: Type, show: Iterable[BooleanValue], value: ExpressionNode) extends Question
