package view.questions

import ast._
import model.DisplayQuestion
import values.Value
import view.widgets.{ NumericTextWidget, QLWidget, SliderWidget, SpinboxWidget }

class NumericQuestion(protected val question: DisplayQuestion, protected val questionStyle: Option[QuestionStyle], protected val updateEnv: Value => Unit) extends GUIQuestion {
  protected override val widget: QLWidget =
    widgetStyle match {
      case Some(w) => w match {
        case Slider(min, max) => new SliderWidget(min, max, questionType, widgetChangeHandler)
        case Spinbox => new SpinboxWidget(questionType, widgetChangeHandler)
        case Textfield => new NumericTextWidget(displayStyle.width, questionType, widgetChangeHandler)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultWidget
    }

  private def defaultWidget = new NumericTextWidget(displayStyle.width, questionType, widgetChangeHandler)

  private def questionType = question.`type` match {
    case n: NumericType => n
    case _ => sys.error(s"Constructing numericQuestion for non numeric question $question")
  }
}
