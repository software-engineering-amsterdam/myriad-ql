package values

import java.text.SimpleDateFormat
import java.util.Date

import ast.{ DecimalType, IntegerType, MoneyType, NumericType }
import com.typesafe.config.ConfigFactory

import scala.math.BigDecimal.RoundingMode

sealed trait Value

case class BooleanValue(value: Boolean) extends Value {
  override def toString = value.toString
}

case class DateValue(value: Date) extends Value {
  private val config = ConfigFactory.load()
  private val dateFormat = new SimpleDateFormat(config.getString("dateFormat"))

  override def toString = dateFormat.format(value)
}

sealed trait NumericValue extends Value {
  val value: BigDecimal
}

case class IntegerValue(value: BigDecimal) extends NumericValue {
  override def toString = value.toString
}

case class DecimalValue(value: BigDecimal) extends NumericValue {
  override def toString = value.toString
}

case class MoneyValue(value: BigDecimal) extends NumericValue {
  private val config = ConfigFactory.load()
  private val currencySymbol = config.getString("currencySymbol")
  override def toString = currencySymbol + value.setScale(2, RoundingMode.HALF_EVEN).toString
}

case class StringValue(value: String) extends Value {
  override def toString = value
}

case object UndefinedValue extends Value {
  override def toString = "Value not defined"
}

object NumericValue {
  def upgradeNumericToType(value: NumericValue, numType: NumericType): NumericValue = (value, numType) match {
    case (MoneyValue(v), MoneyType) => MoneyValue(v)
    case (DecimalValue(v), MoneyType) => MoneyValue(v)
    case (IntegerValue(v), MoneyType) => MoneyValue(v)
    case (DecimalValue(v), DecimalType) => DecimalValue(v)
    case (IntegerValue(v), DecimalType) => DecimalValue(v)
    case (IntegerValue(v), IntegerType) => IntegerValue(v)
    case (v, t) => sys.error(s"Attempt to upgrade value $v to incompatible type $t")
  }

  def bigDecimalToNumericValue(value: BigDecimal, numType: NumericType): NumericValue = numType match {
    case MoneyType => MoneyValue(value)
    case DecimalType => DecimalValue(value)
    case IntegerType => IntegerValue(value.setScale(0))
  }
}
