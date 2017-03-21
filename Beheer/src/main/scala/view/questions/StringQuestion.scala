package view.questions

import ast.QuestionStyle
import model.DisplayQuestion
import values.Value
import view.widgets.{ QLWidget, TextWidget }

class StringQuestion(
    protected val question: DisplayQuestion,
    protected val questionStyle: Option[QuestionStyle],
    protected val updateEnv: Value => Unit
) extends GUIQuestion {

  override protected val widget: QLWidget = new TextWidget(displayStyle.width, widgetChangeHandler)
}
