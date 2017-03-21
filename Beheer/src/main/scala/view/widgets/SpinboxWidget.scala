package view.widgets

import ast.{ DecimalType, IntegerType, MoneyType, NumericType }
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.scene.control.Spinner

class SpinboxWidget(numericType: NumericType, changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {
  override val displayNode: Spinner[Double] = numericType match {
    case IntegerType => new Spinner(Double.MinValue, Double.MaxValue, 0, 1)
    case DecimalType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
    case MoneyType => new Spinner(Double.MinValue, Double.MaxValue, 0, 0.01)
  }

  displayNode.editable = true
  displayNode.value.onChange {
    val qlValue = NumericValue.doubleToNumericValue(displayNode.value.value, numericType)
    this.setValue(qlValue)
    super.handleUpdate(qlValue)
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => displayNode.valueFactory.value.setValue(n.value.toDouble)
    case UndefinedValue => displayNode.valueFactory.value.setValue(0)
    case v => sys.error(s"Incompatible value $v for Spinbox widget")
  }
}
