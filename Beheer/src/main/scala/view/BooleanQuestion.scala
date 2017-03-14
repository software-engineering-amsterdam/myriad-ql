package view

import ast.{ Checkbox, Dropdown, QuestionStyle, Radio }
import model.{ ComputedQuestion, DisplayQuestion }
import view.widgets.{ CheckboxWidget, DropDownWidget, QLWidget, RadioWidget }

class BooleanQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private val widget: QLWidget = questionStyle match {
    case Some(q) => q.widget match {
      case Some(w) => w match {
        case Checkbox => new CheckboxWidget
        case Radio(trueText, falseText) => new RadioWidget(trueText, falseText)
        case Dropdown(trueText, falseText) => new DropDownWidget(trueText, falseText)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }
    case None => defaultWidget
  }

  //If computed: Bind to env.
  question match {
    case c: ComputedQuestion => createValueBinding(c) { value => widget.setValue(value) }
    case _ => Unit
  }

  private def defaultWidget = new RadioWidget("Yes", "No")

  displayBox.children add widget.getSFXNode
}
