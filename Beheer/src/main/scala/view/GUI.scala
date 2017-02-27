package view

import java.io.FileReader

import ast.ExpressionNode.Env
import ast._
import checker.{ Error, FormChecker, Warning }
import parser.FormParser
import values.{ BooleanValue, IntegerValue, Value }

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{ FlowPane, HBox }
import scalafx.scene.text.Text

object GUI extends JFXApp {
  private lazy val questions = parsedForm.displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question, env).element
      case DateType => new DateQuestion(question, env).element
      case StringType => new StringQuestion(question, env).element
      case _: NumericType => new NumericQuestion(question, env).element
    }
  }
  private lazy val issues = {
    val issueMessages = formModel.map {
      case Warning(message) => new HBox(new Text(message))
      case Error(message) => new HBox(new Text(message))
    }
    new HBox {
      children = new HBox(new Text("Errors and warnings:")) +: issueMessages
    }
  }
  private val filename = "src/main/resources/example.ql"
  private val parsedForm = FormParser(new FileReader(filename))
  private val formModel = FormChecker(parsedForm)

  var env: Env = Map(
    "hasSoldHouse" -> BooleanValue(true),
    "sellingPrice" -> IntegerValue(1000)
  )

  def updateEnv(identifier: String, value: Value) = {
    env += (identifier -> value)
    println(env)
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Beheer QL Form"
    width = 600
    height = 450
    scene = new Scene {
      content = new FlowPane {
        hgap = 10
        vgap = 10
        children = issues +: questions
      }
    }
  }
}