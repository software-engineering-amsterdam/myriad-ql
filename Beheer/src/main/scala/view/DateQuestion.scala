package view

import java.time.{ Instant, LocalDate, LocalDateTime, ZoneId }
import java.util.Date
import javafx.beans.binding.ObjectBinding

import ast.DateType
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values._

import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.scene.control.DatePicker

class DateQuestion(val question: DisplayQuestion) extends GUIQuestion {
  val datePicker = new DatePicker()

  question match {
    case c: ComputedQuestion => datePicker.value <== computeDateValue(c)
    case o: OpenQuestion => datePicker.onAction = actionHandler(datePicker, o)
  }

  element.children += datePicker

  private def actionHandler(datePicker: DatePicker, question: OpenQuestion) = () => {
    val value = question.`type` match {
      case DateType => DateValue(datePicker.value.value)
      case _ => UndefinedValue
    }

    updateEnv(question.identifier, value)
  }

  private def computeDateValue(question: ComputedQuestion): ObjectBinding[LocalDate] = new ObjectBinding[LocalDate] {
    bind(env)

    override def computeValue: LocalDate = {
      question.value.value(env.toMap) match {
        case DateValue(d) => d
        case _ => LocalDate.now()
      }
    }
  }

  private implicit def dateToLocalDate(date: Date): LocalDate = {
    val instant = Instant.ofEpochMilli(date.getTime)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    localDateTime.toLocalDate
  }

  private implicit def localDateToDate(localDate: LocalDate): Date = {
    Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant)
  }

}
