import java.io.FileReader

import parser._
import checker.{ Error, FormChecker, Issue, Warning }

object Main extends App {
  val filename = "src/main/resources/example.ql"
  val parsedForm = FormParser(new FileReader(filename))
  val formModel = FormChecker(parsedForm)

  println(parsedForm.questions.mkString("\n"))

  private def printIssues(issues: Iterable[Issue]) = issues.foreach {
    case Warning(message) => println(s"${Console.YELLOW}[WARNING] ${Console.RESET}$message")
    case Error(message) => println(s"${Console.RED}[ERROR] ${Console.RESET}$message")
  }
  printIssues(formModel)
}
