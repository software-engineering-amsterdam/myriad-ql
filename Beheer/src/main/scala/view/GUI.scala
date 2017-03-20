package view

import ast._
import checker.Issue.Issues
import checker.{ Error, Warning }
import model.DisplayQuestion
import view.questions.{ BooleanQuestion, DateQuestion, NumericQuestion, StringQuestion }
import view.style.DisplayStyle

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.layout.{ TilePane, VBox }
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.scene.{ Node, Scene }

trait GUI extends JFXApp.PrimaryStage {
  val issues: Issues

  def displayBoxes: Seq[Node]

  protected def renderQuestion(question: DisplayQuestion, definedStyle: Option[QuestionStyle] = None): VBox = {
    val style = new DisplayStyle(definedStyle)
    question.`type` match {
      case BooleanType => new BooleanQuestion(question, style)
      case DateType => new DateQuestion(question, style)
      case StringType => new StringQuestion(question, style)
      case _: NumericType => new NumericQuestion(question, style)
    }
  }.displayBox

  private def issueBox = issues.map {
    case Error(message) =>
      new Text {
        text = s"[Error] $message"
        fill = Color.Red
      }
    case Warning(message) =>
      new Text {
        text = s"[Warning] $message"
        fill = Color.Yellow
      }
  }

  title.value = "Beheer QL Form"
  width = 600
  height = 800
  scene = new Scene {
    content = new TilePane {
      hgap = 10
      vgap = 10
      padding = Insets(10)
      prefColumns = 1
      children = issueBox ++ displayBoxes
    }
  }
}