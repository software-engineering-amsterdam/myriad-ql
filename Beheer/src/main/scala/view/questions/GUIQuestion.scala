package view.questions

import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ Evaluator, Value }
import view.style.DisplayStyle
import view.widgets.QLWidget
import view.{ env, updateEnv }

import scalafx.beans.binding.{ Bindings, BooleanBinding }
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

abstract class GUIQuestion(question: DisplayQuestion, displayStyle: DisplayStyle) {
  protected val widget: QLWidget
  protected val widgetUpdateHandler: Option[Value => Unit] = question match {
    case o: OpenQuestion => Some(updateEnv(o.identifier))
    case _: ComputedQuestion => None
  }

  question match {
    case c: ComputedQuestion => Bindings.createObjectBinding(() => Evaluator(env.toMap).calculate(c.value), env).onChange {
      (newValue, _, _) =>
        {
          widget.setValue(newValue.value)
          updateEnv(c.identifier)(newValue.value)
        }
    }
    case _: OpenQuestion => Unit
  }
  private val label: Text = new Text {
    text = question.label
    font = displayStyle.font
    fill = displayStyle.fill
  }

  //Depends on potentially child-class overridden val, therefore must be lazy to delay instantiation.
  lazy val displayBox = new VBox {
    children = Seq(label, widget.displayNode)
    disable = isDisabled(question)
    visible <== isVisible(question)
  }

  private def extractStyle[T](styling: Styling, pf: PartialFunction[Style, T]): Option[T] =
    styling.values.collect(pf).lastOption

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  private def isVisible(question: DisplayQuestion): BooleanBinding =
    Bindings.createBooleanBinding(() => Evaluator(env.toMap).display(question.displayConditions), env)
}
