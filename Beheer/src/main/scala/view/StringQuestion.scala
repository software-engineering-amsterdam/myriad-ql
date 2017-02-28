package view

import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values._

import scalafx.Includes._
import scalafx.scene.control.TextField

class StringQuestion(val question: DisplayQuestion) extends GUIQuestion {

  val textField = new TextField()

  question match {
    case c: ComputedQuestion => textField.text <== computeValue(c)
    case o: OpenQuestion => textField.onAction = actionHandler(textField, o)
  }

  element.children += textField

  private def actionHandler(textField: TextField, question: OpenQuestion) = () => {
    val value = StringValue(textField.text.value)
    updateEnv(question.identifier, value)
  }
}
