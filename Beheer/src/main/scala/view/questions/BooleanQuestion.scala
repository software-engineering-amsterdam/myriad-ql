package view.questions

import ast.{ Checkbox, Dropdown, QuestionStyle, Radio }
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import view.widgets.{ CheckboxWidget, DropDownWidget, QLWidget, RadioWidget }

class BooleanQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private val widget: QLWidget = questionStyle match {
    case Some(q) => q.widget match {
      case Some(w) => w match {
        case Checkbox => new CheckboxWidget
        case Radio(trueText, falseText) => new RadioWidget(trueText, falseText)
        case Dropdown(trueText, falseText) => new DropDownWidget(trueText, falseText, width)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }
    case None => defaultWidget
  }

  question match {
    case c: ComputedQuestion => createValueBinding(c) { value => widget.setValue(value) }
    case _: OpenQuestion => Unit
  }

  private def defaultWidget = new RadioWidget("Yes", "No")

  displayBox.children add widget.getSFXNode
}
