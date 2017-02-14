import java.io.FileReader

import parser.{ FormChecker, FormParser }

object Main extends App {
  val filename = "src/main/resources/example.ql"
  val parsedForm = FormParser(new FileReader(filename))
  FormChecker(parsedForm) match {
    case Left(issues) => println(issues.mkString("\n"))
    case Right((form, warnings)) =>
      println(form.questions.mkString("\n"))
      println(warnings.mkString("\n"))
  }
}
