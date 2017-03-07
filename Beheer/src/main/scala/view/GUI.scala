package view

import java.io.FileReader

import ast._
import checker.{ Error, FormChecker, StyleChecker, Warning }
import parser.{ FormParser, StylesheetParser }

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{ HBox, TilePane, VBox }
import scalafx.scene.text.Text

object GUI extends JFXApp {
  private lazy val displayBoxes = formModel.displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question).element
      case DateType => new DateQuestion(question).element
      case StringType => new StringQuestion(question).element
      case _: NumericType => new NumericQuestion(question).element
    }
  }
  private lazy val issues = {
    val issueMessages = (formIssues ++ styleIssues).map {
      case Warning(message) => new HBox(new Text(message))
      case Error(message) => new HBox(new Text(message))
    }
    new VBox {
      children = issueMessages
    }
  }

  private val formFile = "src/main/resources/example.ql"
  private val formModel = FormParser(new FileReader(formFile))
  private val formIssues = FormChecker(formModel)

  private val styleFile = "src/main/resources/example.qls"
  private val styleModel = StylesheetParser(new FileReader(styleFile))
  private val styleIssues = StyleChecker(styleModel, formModel.identifiersWithType.toMap)

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
        children = issues +: displayBoxes
      }
    }
  }
}