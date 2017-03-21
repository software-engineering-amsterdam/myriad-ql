package view.questions

import ast.QuestionStyle
import model.DisplayQuestion
import view.style.DisplayStyle
import view.widgets.{ QLWidget, TextWidget }

class StringQuestion(question: DisplayQuestion, questionStyle: DisplayStyle) extends GUIQuestion(question, questionStyle) {
  override protected val widget: QLWidget = new TextWidget(questionStyle.width, widgetUpdateHandler)
}
