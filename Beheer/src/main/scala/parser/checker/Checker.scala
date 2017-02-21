package parser.checker

sealed trait Issue

case class Error(message: String) extends Issue

case class Warning(message: String) extends Issue

trait Checker {
  type Issues = Seq[Issue]
  type Errors = Seq[Error]
  type Warnings = Seq[Warning]

  def check: Issues
}
