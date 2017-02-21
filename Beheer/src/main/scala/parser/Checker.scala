package parser

sealed trait Issue

case class Error(message: String) extends Issue

case class Warning(message: String) extends Issue

trait Checker[O] {
  type Issues = Seq[Issue]
  type Errors = Seq[Error]
  type Warnings = Seq[Warning]

  def check: Either[Issues, (O, Warnings)]
}
