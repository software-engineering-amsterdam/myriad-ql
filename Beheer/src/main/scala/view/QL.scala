package view

import checker.Issue.Issues
import model.DisplayQuestion

class QL(val issues: Issues, val displayQuestions: Seq[DisplayQuestion]) extends GUI {
  override def displayBoxes = displayQuestions.map(question => renderQuestion(question))
}

object QL {
  def apply(issues: Issues, displayQuestions: Seq[DisplayQuestion]) = new QL(issues, displayQuestions)
}