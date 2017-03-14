package view

import ast.Stylesheet.Blocks
import ast._
import checker.Issue.Issues
import model.DisplayQuestion

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, TilePane, VBox}
import scalafx.scene.text.Text

class GUI(issues: Issues, displayQuestions: Seq[DisplayQuestion], questionStyles: Seq[(Page, Blocks)]) extends JFXApp.PrimaryStage {
  private val displayBoxes = displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question)
      case DateType => new DateQuestion(question)
      case StringType => new StringQuestion(question)
      case _: NumericType => new NumericQuestion(question)
    }
  }.map(_.displayBox)

  private val issueBox = {
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

object GUI extends JFXApp {
  def apply(issues: Issues, displayQuestions: Seq[DisplayQuestion], questionStyles: Seq[(Page, Blocks)]) =
    new GUI(issues, displayQuestions, questionStyles)
}
