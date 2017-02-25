package view

import java.io.FileReader

import ast._
import checker.{Error, FormChecker, Issue, Warning}
import parser.FormParser
import values.{BooleanValue, IntegerValue, Value}

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.FlowPane

object GUI extends JFXApp {
  var env: Map[String, Value] = Map(
    "hasSoldHouse" -> BooleanValue(true),
    "sellingPrice" -> IntegerValue(1000)
  )

  def updateEnv(identifier: String, value: Value) = {
    env + identifier -> value
  }

  val filename = "src/main/resources/example.ql"
  val parsedForm = FormParser(new FileReader(filename))
  val formModel = FormChecker(parsedForm)

  println(parsedForm.displayQuestions.mkString("\n"))

  printIssues(formModel)

  val questions = parsedForm.displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question, env).element
      case DateType => new DateQuestion(question, env).element
      case StringType => new StringQuestion(question, env).element
      case _: NumericType => new NumericQuestion(question, env).element
    }
  }

  private def printIssues(issues: Iterable[Issue]) = issues.foreach {
    case Warning(message) => println(s"${Console.YELLOW}[WARNING] ${Console.RESET}$message")
    case Error(message) => println(s"${Console.RED}[ERROR] ${Console.RESET}$message")
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Beheer QL Form"
    width = 600
    height = 450
    scene = new Scene {
      content = new FlowPane {
        hgap = 10
        vgap = 10
        title = "QL Form"
        children = questions
      }
    }
  }
}