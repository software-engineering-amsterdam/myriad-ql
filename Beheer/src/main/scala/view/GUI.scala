package view

import ast.Stylesheet.Blocks
import ast._
import checker.Issue.Issues
import model.DisplayQuestion

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{ HBox, TilePane, VBox }
import scalafx.scene.text.Text

class GUI(issues: Issues, displayQuestions: Seq[DisplayQuestion], pages: Seq[Blocks]) extends JFXApp.PrimaryStage {

  private def renderQuestion(question: DisplayQuestion, style: Option[QuestionStyle] = None): VBox = {
    question.`type` match {
      case BooleanType => new BooleanQuestion(question, style)
      case DateType => new DateQuestion(question, style)
      case StringType => new StringQuestion(question, style)
      case _: NumericType => new NumericQuestion(question, style)
    }
  }.displayBox

  private def renderSection(section: Section): Seq[VBox] = {
    val label = new VBox { children = new Text(section.label) }
    val blocks = section.blocks.flatMap(block => renderBlock(block))
    label +: blocks
  }

  private def renderBlock(block: Block): Seq[VBox] = block match {
    case s: Section => renderSection(s)
    case q: QuestionStyle => Seq(renderQuestion(getQuestion(q.identifier), Some(q)))
  }

  private def renderPage(blocks: Blocks): Seq[VBox] = {
    blocks.flatMap(block => renderBlock(block))
  }

  private def renderQLS = pages.flatMap(page => renderPage(page))

  private def getQuestion(identifier: String): DisplayQuestion = {
    displayQuestions.find(_.identifier == identifier) match {
      case Some(question) => question
      case None => sys.error("Question from stylesheet not found in list of questions")
    }
  }

  private val displayBoxes = displayQuestions.map { question =>
    renderQuestion(question, None)
  }

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
      children = issueBox +: renderQLS
    }
  }
}

object GUI extends JFXApp {
  def apply(issues: Issues, displayQuestions: Seq[DisplayQuestion], pages: Seq[Blocks]) =
    new GUI(issues, displayQuestions, pages)
}
