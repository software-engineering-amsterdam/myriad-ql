package model

import parser.ast.{ ExpressionNode, Type }

case class Form(questions: Seq[Question])

sealed trait Question {
  val identifier: String
  val label: String
  val show: Seq[ExpressionNode]
  val `type`: Type
}

case class OpenQuestion(identifier: String, label: String, show: Seq[ExpressionNode], `type`: Type) extends Question

case class ComputedQuestion(identifier: String, label: String, show: Seq[ExpressionNode], `type`: Type, value: ExpressionNode) extends Question
