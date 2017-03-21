package view

import ast._
import checker.Issue.Issues
import model.DisplayQuestion
import model.DisplayQuestion.DisplayQuestions

import scalafx.scene.Node
import scalafx.scene.text.{ Font, FontWeight, Text }

class QLS(val issues: Issues, val displayQuestions: DisplayQuestions, stylesheet: Stylesheet) extends GUI {
  override def displayBoxes = stylesheet.pages.flatMap(page => renderPage(page))

  private def renderPage(page: Page): Seq[Node] =
    page.sections.flatMap(section => renderBlock(section))

  private def renderBlock(block: Block): Seq[Node] = block match {
    case s: Section => renderSection(s)
    case q: QuestionStyle => Seq(renderQuestion(getQuestion(q.identifier), Some(q)))
  }

  private def renderSection(section: Section): Seq[Node] = {
    val label = new Text {
      text = section.label
      font = Font.font(null, FontWeight.Bold, 18)
    }
    val blocks = section.blocks.flatMap(block => renderBlock(block))
    label +: blocks
  }

  private def getQuestion(identifier: String): DisplayQuestion = {
    displayQuestions.find(_.identifier == identifier) match {
      case Some(question) => question
      case None => sys.error("Question from stylesheet not found in list of questions")
    }
  }
}

object QLS {
  def apply(issues: Issues, displayQuestions: DisplayQuestions, stylesheet: Stylesheet): QLS =
    new QLS(issues, displayQuestions, stylesheet)
}