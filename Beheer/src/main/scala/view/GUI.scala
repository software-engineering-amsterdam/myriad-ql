package view

import ast._
import checker.Issue.Issues
import model.DisplayQuestion
import view.questions.{ BooleanQuestion, DateQuestion, NumericQuestion, StringQuestion }

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{ HBox, TilePane, VBox }
import scalafx.scene.text.Text

trait GUI extends JFXApp.PrimaryStage {
  val issues: Issues

  def displayBoxes: Seq[VBox]

  protected def renderQuestion(question: DisplayQuestion, style: Option[QuestionStyle] = None): VBox = {
    question.`type` match {
      case BooleanType => new BooleanQuestion(question, style)
      case DateType => new DateQuestion(question, style)
      case StringType => new StringQuestion(question, style)
      case _: NumericType => new NumericQuestion(question, style)
    }
  }.displayBox

  private def issueBox = {
    val issueMessages = issues.map(issue => new HBox(new Text(issue.message)))
    new VBox {
      children = issueMessages
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
      children = issueBox +: displayBoxes
    }
  }
}