package view.widgets

import java.time.{ Instant, LocalDate, LocalDateTime, ZoneId }
import java.util.Date

import values.{ DateValue, UndefinedValue, Value }

import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control.DatePicker

class DateWidget(protected val changeHandler: Option[Value => Unit]) extends QLWidget {

  private val datePicker: DatePicker = new DatePicker()

  datePicker.onAction = handle {
    changeHandler.foreach((f) => f(DateValue(datePicker.value.value)))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case DateValue(date) => datePicker.value = date
    case UndefinedValue => datePicker.value = LocalDate.now
    case v => sys.error(s"Incompatible value $v for Date widget.")
  }

  override def displayNode: Node = datePicker

  private implicit def dateToLocalDate(date: Date): LocalDate = {
    val instant = Instant.ofEpochMilli(date.getTime)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    localDateTime.toLocalDate
  }

  private implicit def localDateToDate(localDate: LocalDate): Date = {
    Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant)
  }
}
