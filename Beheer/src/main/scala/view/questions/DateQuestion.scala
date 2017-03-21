package view.questions

import ast.QuestionStyle
import model.DisplayQuestion
import view.style.DisplayStyle
import view.widgets.{ DateWidget, QLWidget }

import scala.language.implicitConversions

class DateQuestion(question: DisplayQuestion, questionStyle: DisplayStyle) extends GUIQuestion(question, questionStyle) {
  protected override val widget: QLWidget = new DateWidget(widgetUpdateHandler)
}
