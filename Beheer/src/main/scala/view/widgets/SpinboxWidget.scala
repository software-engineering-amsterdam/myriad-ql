package view.widgets

import ast.{ DecimalType, IntegerType, MoneyType, NumericType }
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.scene.control.Spinner

class SpinboxWidget(numericType: NumericType)(implicit val changeHandler: Value => Unit) extends QLWidget {
  private val spinbox: Spinner[Double] = numericType match {
    case IntegerType => new Spinner(Double.MinValue, Double.MaxValue, 0, 1)
    case DecimalType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
    case MoneyType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
  }

  spinbox.editable = true
  spinbox.value.onChange {
    val qlValue = NumericValue.doubleToNumericValue(spinbox.value.value, numericType)
    this.setValue(qlValue)
    changeHandler(qlValue)
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => spinbox.valueFactory.value.setValue(n.value.toDouble)
    case UndefinedValue => spinbox.valueFactory.value.setValue(0)
    case v => sys.error(s"Incompatible value $v for Spinbox widget")
  }

  override def getSFXNode = spinbox

}
