package view

import ast.{ DecimalType, IntegerType, MoneyType }
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ DecimalValue, IntegerValue, MoneyValue, UndefinedValue }

import scala.util.{ Failure, Success, Try }
import scalafx.Includes._
import scalafx.scene.control.TextField

class NumericQuestion(val question: DisplayQuestion) extends GUIQuestion {

  val textField = new TextField()

  question match {
    case c: ComputedQuestion => textField.text <== computeValue(c)
    case o: OpenQuestion => textField.onAction = actionHandler(textField, o)
  }

  displayBox.children += textField

  private def actionHandler(textField: TextField, question: OpenQuestion) = () => {
    val value = Try(textField.text.value.toDouble) match {
      case Success(d) => question.`type` match {
        case MoneyType => MoneyValue(d)
        case DecimalType => DecimalValue(d)
        case IntegerType => IntegerValue(d)
        case _ => UndefinedValue
      }
      case Failure(_) => UndefinedValue
    }

    updateEnv(question.identifier, value)
  }
}
