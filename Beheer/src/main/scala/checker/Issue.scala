package checker

sealed trait Issue {
  val message: String
}

case class Error(message: String) extends Issue

case class Warning(message: String) extends Issue

object Issue {
  type Issues = Seq[Issue]
  type Errors = Seq[Error]
  type Warnings = Seq[Warning]
}
