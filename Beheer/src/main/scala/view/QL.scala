package view

import checker.Issue.Issues
import model.DisplayQuestion.DisplayQuestions

class QL(val issues: Issues, val displayQuestions: DisplayQuestions) extends GUI {
  override def displayBoxes = displayQuestions.map(question => renderQuestion(question))
}

object QL {
  def apply(issues: Issues, displayQuestions: DisplayQuestions) = new QL(issues, displayQuestions)
}