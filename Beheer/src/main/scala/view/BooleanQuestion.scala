package view

import ast.{ Checkbox, Dropdown, QuestionStyle, Radio }
import model.DisplayQuestion
import values.BooleanValue
import view.widgets.{ CheckboxWidget, DropDownWidget, QLWidget, RadioWidget }

import scala.language.implicitConversions

class BooleanQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private def defaultWidget = new RadioWidget("Yes", "No")

  private val widget: QLWidget[BooleanValue] = questionStyle match {
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

  displayBox.children add widget.getSFXNode
}
