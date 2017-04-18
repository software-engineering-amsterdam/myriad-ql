package values

import java.text.SimpleDateFormat
import java.util.Date

import ast.{ DecimalType, IntegerType, MoneyType, NumericType }
import com.typesafe.config.ConfigFactory

import scala.math.BigDecimal.RoundingMode
import scala.util.{ Failure, Success, Try }

sealed trait Value

case class BooleanValue(value: Boolean) extends Value {
  override def toString = value.toString
}

case class DateValue(value: Date) extends Value {
  override def toString = DateValue.dateFormat.format(value)
}

object DateValue {
  val dateFormat = new SimpleDateFormat(ConfigFactory.load.getString("dateFormat"))
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
  override def toString = NumericValue.currencySymbol + value.setScale(2, RoundingMode.HALF_EVEN).toString
}

case class StringValue(value: String) extends Value {
  override def toString = value
}

case object UndefinedValue extends Value {
  override def toString = "Value not defined"
}

object NumericValue {
  val currencySymbol = ConfigFactory.load.getString("currencySymbol")

  def upgradeNumericToType(value: NumericValue, numType: NumericType): NumericValue = (value, numType) match {
    case (MoneyValue(v), MoneyType) => MoneyValue(v)
    case (DecimalValue(v), MoneyType) => MoneyValue(v)
    case (IntegerValue(v), MoneyType) => MoneyValue(v)
    case (DecimalValue(v), DecimalType) => DecimalValue(v)
    case (IntegerValue(v), DecimalType) => DecimalValue(v)
    case (IntegerValue(v), IntegerType) => IntegerValue(v)
    case (v, t) => sys.error(s"Attempt to upgrade value $v to incompatible type $t")
  }

  def doubleToNumericValue(value: Double, numType: NumericType): NumericValue =
    bigDecimalToNumericValue(BigDecimal(value), numType)

  def stringToNumericValue(value: String, numType: NumericType): Value = {
    val parseResult = numType match {
      case MoneyType => Try(BigDecimal(value.trim.stripPrefix(currencySymbol)))
      case DecimalType => Try(BigDecimal(value))
      case IntegerType => Try(BigDecimal(value).setScale(0))
    }
    parseResult match {
      case Success(decimal) => bigDecimalToNumericValue(decimal, numType)
      case Failure(_) => UndefinedValue
    }
  }

  def bigDecimalToNumericValue(value: BigDecimal, numType: NumericType): NumericValue = numType match {
    case MoneyType => MoneyValue(value)
    case DecimalType => DecimalValue(value)
    case IntegerType => IntegerValue(value.setScale(0))
  }
}
