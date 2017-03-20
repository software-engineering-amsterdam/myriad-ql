package view.questions

import ast._
import model.DisplayQuestion
import view.style.DisplayStyle
import view.widgets.{ NumericTextWidget, QLWidget, SliderWidget, SpinboxWidget }

class NumericQuestion(question: DisplayQuestion, questionStyle: DisplayStyle) extends GUIQuestion(question, questionStyle) {
  protected override val widget: QLWidget =
    questionStyle.widget match {
      case Some(w) => w match {
        case Slider(min, max) => new SliderWidget(min, max, questionType, widgetUpdateHandler)
        case Textfield => new NumericTextWidget(questionStyle.width, questionType, widgetUpdateHandler)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }

  private def defaultWidget = new SpinboxWidget(questionType, widgetUpdateHandler)

  private def questionType = question.`type` match {
    case n: NumericType => n
    case _ => sys.error(s"Constructing numericQuestion for non numeric question $question")
  }
}
