package view.questions

import ast.{ Checkbox, Dropdown, QuestionStyle, Radio }
import model.DisplayQuestion
import values.Value
import view.widgets.{ CheckboxWidget, DropDownWidget, QLWidget, RadioWidget }

class BooleanQuestion(protected val question: DisplayQuestion, protected val questionStyle: Option[QuestionStyle], protected val updateEnv: Value => Unit) extends GUIQuestion {
  override val widget: QLWidget =
    widgetStyle match {
      case Some(w) => w match {
        case Checkbox => new CheckboxWidget(widgetChangeHandler)
        case Radio(trueText, falseText) => new RadioWidget(trueText, falseText, widgetChangeHandler)
        case Dropdown(trueText, falseText) => new DropDownWidget(trueText, falseText, displayStyle.width, widgetChangeHandler)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }

  private def defaultWidget = new RadioWidget("Yes", "No", widgetChangeHandler)
}
