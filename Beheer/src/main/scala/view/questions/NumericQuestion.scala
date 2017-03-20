package view.questions

import ast._
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import view.widgets.{ NumericTextWidget, SliderWidget, SpinboxWidget }

class NumericQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private val widget = questionStyle match {
    case Some(q) => q.widget match {
      case Some(w) => w match {
        case Slider(min, max) => new SliderWidget(min, max, questionType)
        case Textfield => new NumericTextWidget(width, questionType)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }
    case None => defaultWidget
  }

  private def defaultWidget = new SpinboxWidget(questionType)

  private def questionType = question.`type` match {
    case n: NumericType => n
    case _ => sys.error(s"Constructing numericQuestion for non numeric question $question")
  }

  question match {
    case c: ComputedQuestion => createValueBinding(c) { newVal => widget.setValue(newVal) }
    case _: OpenQuestion => Unit
  }

  displayBox.children add widget.getSFXNode
}
