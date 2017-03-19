package view

import ast.Stylesheet.Blocks
import ast.{ Block, QuestionStyle, Section }
import checker.Issue.Issues
import model.DisplayQuestion

import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

class QLS(val issues: Issues, val displayQuestions: Seq[DisplayQuestion], pages: Seq[Blocks]) extends GUI {
  override def displayBoxes = renderQLS

  private def renderSection(section: Section): Seq[VBox] = {
    val label = new VBox {
      children = new Text(section.label)
    }
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
}

object QLS {
  def apply(issues: Issues, displayQuestions: Seq[DisplayQuestion], pages: Seq[Blocks]): QLS =
    new QLS(issues, displayQuestions, pages)
}