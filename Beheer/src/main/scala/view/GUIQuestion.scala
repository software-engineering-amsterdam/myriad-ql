package view

import javafx.beans.binding.{ BooleanBinding, StringBinding }

import ast.Stylesheet.Styling
import ast._
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ Evaluator, Value }

import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

import scala.language.implicitConversions

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

  protected def computeValue(question: ComputedQuestion): StringBinding = new StringBinding {
    bind(env)

    override def computeValue: String = Evaluator(env.toMap, question.value).toString
  }

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

  private def isVisible(question: DisplayQuestion): BooleanBinding = new BooleanBinding {
    bind(env)

    override def computeValue: Boolean = question.show(env.toMap)
  }
}