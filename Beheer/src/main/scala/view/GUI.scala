package view

import java.io.FileReader

import ast._
import checker.{ Error, FormChecker, Warning }
import parser.FormParser

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{ HBox, TilePane, VBox }
import scalafx.scene.text.Text

object GUI extends JFXApp {
  private lazy val questions = parsedForm.displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question).element
      case DateType => new DateQuestion(question).element
      case StringType => new StringQuestion(question).element
      case _: NumericType => new NumericQuestion(question).element
    }
  }
  private lazy val issues = {
    val issueMessages = formModel.map {
      case Warning(message) => new HBox(new Text(message))
      case Error(message) => new HBox(new Text(message))
    }
    new VBox {
      children = issueMessages
    }
  }

  private val filename = "src/main/resources/example.ql"
  private val parsedForm = FormParser(new FileReader(filename))
  private val formModel = FormChecker(parsedForm)

  stage = new JFXApp.PrimaryStage {
    title.value = "Beheer QL Form"
    width = 600
    height = 800
    scene = new Scene {
      content = new TilePane {
        hgap = 10
        vgap = 10
        padding = Insets(10)
        prefColumns = 1
        children = issues +: questions
      }
    }
  }
}