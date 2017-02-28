package view

import javafx.beans.binding.{ BooleanBinding, StringBinding }

import ast.ExpressionNode
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }

import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

trait GUIQuestion {
  val question: DisplayQuestion

  val textLabel = new Text(question.label)

  val element = new VBox {
    children = Seq(textLabel)
    disable = isDisabled(question)
    visible <== isVisible(question)
  }

  def computeValue(question: ComputedQuestion): StringBinding = new StringBinding {
    bind(env)

    override def computeValue = ExpressionNode.calculate(env.toMap, question.value).display
  }

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  private def isVisible(question: DisplayQuestion): BooleanBinding = new BooleanBinding {
    bind(env)

    override def computeValue: Boolean = question.show(env.toMap)
  }
}