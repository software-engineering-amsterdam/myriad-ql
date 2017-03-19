package view

import ast._
import model.{ ComputedQuestion, DisplayQuestion }
import view.widgets.{ NumericTextWidget, SliderWidget }

class NumericQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private def questionType = question.`type` match {
    case n: NumericType => n
    case _ => sys.error(s"Constructing numericQuestion for non numeric question $question")
  }
  private def defaultWidget = new SliderWidget(0, 1000.0, questionType)
  private val widget = questionStyle match {
    case Some(q) => q.widget match {
      case Some(w) => defaultWidget
      case None => defaultWidget
    }
    case None => defaultWidget
  }

  question match {
    case c: ComputedQuestion => createValueBinding(c) { newVal => widget.setValue(newVal) }
    case _ => Unit
  }

  displayBox.children add widget.getSFXNode
}
