package view.questions

import ast.QuestionStyle
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values._
import view.updateEnv

import scalafx.Includes._
import scalafx.scene.control.TextField

class StringQuestion(val question: DisplayQuestion, val questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {

  val textField = new TextField()
  textField.setPrefWidth(width)

  question match {
    case c: ComputedQuestion => textField.text <== computeValue(c)
    case o: OpenQuestion => textField.onAction = actionHandler(textField, o)
  }

  displayBox.children += textField

  private def actionHandler(textField: TextField, question: OpenQuestion) = () => {
    val value = StringValue(textField.text.value)
    updateEnv(question.identifier, value)
  }
}
