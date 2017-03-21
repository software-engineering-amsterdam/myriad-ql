package view.questions

import ast.QuestionStyle
import model.DisplayQuestion
import values.Value
import view.widgets.{ DateWidget, QLWidget }

import scala.language.implicitConversions

class DateQuestion(
    protected val question: DisplayQuestion,
    protected val questionStyle: Option[QuestionStyle],
    protected val updateEnv: Value => Unit
) extends GUIQuestion {

  protected override val widget: QLWidget = new DateWidget(widgetChangeHandler)
}
