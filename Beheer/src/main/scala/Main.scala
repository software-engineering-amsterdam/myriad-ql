import java.io.FileReader

import checker._
import parser.{ FormParser, StylesheetParser }
import view.GUI

import scalafx.application.JFXApp

object Main extends JFXApp {
  private val formFile = "src/main/resources/example.ql"
  private val formModel = FormParser(new FileReader(formFile))
  private val formIssues = FormChecker(formModel)

  private val styleFile = "src/main/resources/example.qls"
  private val styleModel = StylesheetParser(new FileReader(styleFile))
  private val styleIssues = StyleChecker(styleModel, formModel.identifiersWithType.toMap)
  private val issues = formIssues ++ styleIssues

  private def printIssues(issues: Iterable[Issue]) = issues.foreach {
    case Warning(message) => println(s"${Console.YELLOW}[WARNING] ${Console.RESET}$message")
    case Error(message) => println(s"${Console.RED}[ERROR] ${Console.RESET}$message")
  }

  printIssues(issues)
  stage = GUI(issues, formModel.displayQuestions, styleModel.questionStyles)
}
