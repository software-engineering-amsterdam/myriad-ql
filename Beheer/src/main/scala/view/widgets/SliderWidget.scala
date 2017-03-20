package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.beans.property.DoubleProperty
import scalafx.scene.Node
import scalafx.scene.control.Slider

class SliderWidget(min: BigDecimal, max: BigDecimal, numericType: NumericType, changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {
  private val selectedValue: DoubleProperty = new DoubleProperty()

  selectedValue.onChange {
    val qlValue = NumericValue.doubleToNumericValue(selectedValue.value, numericType)
    this.setValue(qlValue)
    super.handleUpdate(qlValue)
  }

  override val displayNode: Slider = new Slider {
    min = min.doubleValue
    max = max.doubleValue
    value <==> selectedValue
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue =>
      if (n.value >= min && n.value <= max) {
        selectedValue.value_=(n.value)
      } else if (n.value < min) {
        selectedValue.value_=(min)
      } else {
        selectedValue.value_=(max)
      }
    case UndefinedValue => selectedValue.value_=(min)
    case v => sys.error(s"Incompatible value $v for Slider Widget")
  }
}
