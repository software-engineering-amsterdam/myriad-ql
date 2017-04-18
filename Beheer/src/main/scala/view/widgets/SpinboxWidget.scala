package view.widgets

import ast.{ DecimalType, IntegerType, MoneyType, NumericType }
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.scene.Node
import scalafx.scene.control.Spinner

class SpinboxWidget(numericType: NumericType, protected val changeHandler: Option[Value => Unit]) extends QLWidget {
  private val spinner: Spinner[Double] = numericType match {
    case IntegerType => new Spinner(Double.MinValue, Double.MaxValue, 0, 1)
    case DecimalType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
    case MoneyType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
  }

  spinner.editable = true
  spinner.value.onChange {
    val qlValue = NumericValue.doubleToNumericValue(spinner.value.value, numericType)
    setValue(qlValue)
    changeHandler.foreach((f) => f(qlValue))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => spinner.valueFactory.value.setValue(n.value.toDouble)
    case UndefinedValue => spinner.valueFactory.value.setValue(0)
    case v => sys.error(s"Incompatible value $v for Spinbox widget")
  }

  override def displayNode: Node = spinner
}
