package view.widgets

import java.time.{ Instant, LocalDate, LocalDateTime, ZoneId }
import java.util.Date

import values.{ DateValue, UndefinedValue, Value }

import scala.language.implicitConversions
import scalafx.Includes._
import scalafx.scene.control.DatePicker

class DateWidget(changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {

  override val displayNode: DatePicker = new DatePicker()

  displayNode.onAction = handle {
    super.handleUpdate(DateValue(displayNode.value.value))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case DateValue(date) => displayNode.value_=(date)
    case UndefinedValue => displayNode.value_=(LocalDate.now)
    case v => sys.error(s"Incompatible value $v for Date widget.")
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
