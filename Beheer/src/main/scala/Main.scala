import java.io.FileReader

import parser.{ FormParser, TypeChecker }

object Main extends App {
  val filename = "src/main/resources/example.ql"
  val parsedForm = FormParser(new FileReader(filename))
  TypeChecker(parsedForm) match {
    case Left(errors) => println(errors)
    case Right((form, warnings)) =>
      println(form.questions.mkString("\n"))
      println(warnings.mkString("\n"))
  }
}
