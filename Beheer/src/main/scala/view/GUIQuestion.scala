package view

import javafx.beans.binding.{ BooleanBinding, StringBinding }

import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }

import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

trait GUIQuestion {
  val question: DisplayQuestion

  val textLabel = new Text(question.label)

  val element = new HBox {
    children = Seq(textLabel)
    disable = isDisabled(question)
    visible <== isVisible(question)
  }

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  private def isVisible(question: DisplayQuestion): BooleanBinding = new BooleanBinding {
    bind(env)

    override def computeValue: Boolean = question.show(env.toMap)
  }

  def computeValue(question: ComputedQuestion): StringBinding = new StringBinding {
    bind(env)

    override def computeValue = question.value.value(env.toMap).display
  }
}