package values

import java.text.SimpleDateFormat
import java.util.Date

case class DateValue(value: Date) extends Value {
  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

  override def display = dateFormat.format(value)
}

