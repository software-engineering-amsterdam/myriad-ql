package view

import ast.Stylesheet.Styling
import ast._
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ Evaluator, Value }

import scala.language.implicitConversions
import scalafx.beans.binding.{ Bindings, BooleanBinding, StringBinding }
import scalafx.event.subscriptions.Subscription
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

trait GUIQuestion {
  val question: DisplayQuestion
  val questionStyle: Option[QuestionStyle]

  implicit protected def widgetUpdateHandler(newVal: Value): Unit = updateEnv(question.identifier, newVal)

  val displayBox = new VBox {
    children = Seq(createLabel)
    disable = isDisabled(question)
    visible <== isVisible(question)
  }

  protected val width: Double = questionStyle.flatMap(_.styling.values.collect {
    case Width(value) => value
  }.lastOption.map(_.toDouble)).getOrElse(100.0)

  protected def createValueBinding(c: ComputedQuestion)(changeHandler: Value => Unit): Subscription =
    Bindings.createObjectBinding[Value](() => Evaluator(env.toMap, c.value), env).onChange((newValue, _, _) => changeHandler(newValue.value))

  protected def computeValue(question: ComputedQuestion): StringBinding =
    Bindings.createStringBinding(() => Evaluator(env.toMap, question.value).toString, env)

  private def createLabel: Text = {
    questionStyle match {
      case Some(d) =>
        val s = toCSS(d.styling)
        new Text {
          text = question.label
          style = s
        }
      case None =>
        new Text {
          text = question.label
        }
    }
  }

  private def toCSS(styling: Styling): String = styling.values.collect {
    case Color(value) => s"-fx-fill : $value"
    case Font(value) => s"-fx-font : $value"
    case FontSize(value) => s"-fx-font-size : $value"
  }.mkString("\n")

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  private def isVisible(question: DisplayQuestion): BooleanBinding =
    Bindings.createBooleanBinding(() => question.show(env.toMap), env)
}
