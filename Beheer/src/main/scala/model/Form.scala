package model

sealed trait Type

case object Date extends Type

case object String extends Type

case object Money extends Type

case object Decimal extends Type

case object Integer extends Type

case object Boolean extends Type

case class Form(questions: Seq[Question])

sealed trait Question {
  val identifier: String
  val label: String
  val show: Iterable[BooleanValue]
  val `type`: Type
}

case class OpenQuestion(identifier: String, label: String, show: Iterable[BooleanValue], `type`: Type) extends Question

case class ComputedQuestion(identifier: String, label: String, show: Iterable[BooleanValue], `type`: Type, value: Expression) extends Question
