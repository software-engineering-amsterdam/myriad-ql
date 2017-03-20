package view.questions

import ast.{ Checkbox, Dropdown, Radio }
import model.DisplayQuestion
import view.style.DisplayStyle
import view.widgets.{ CheckboxWidget, DropDownWidget, QLWidget, RadioWidget }

class BooleanQuestion(question: DisplayQuestion, questionStyle: DisplayStyle) extends GUIQuestion(question, questionStyle) {
  override val widget: QLWidget =
    questionStyle.widget match {
      case Some(w) => w match {
        case Checkbox => new CheckboxWidget(widgetUpdateHandler)
        case Radio(trueText, falseText) => new RadioWidget(trueText, falseText, widgetUpdateHandler)
        case Dropdown(trueText, falseText) => new DropDownWidget(trueText, falseText, questionStyle.width, widgetUpdateHandler)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }

  private def defaultWidget = new RadioWidget("Yes", "No", widgetUpdateHandler)
}
