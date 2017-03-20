package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.beans.property.DoubleProperty
import scalafx.scene.Node
import scalafx.scene.control.Slider

class SliderWidget(min: BigDecimal, max: BigDecimal, numericType: NumericType)(implicit val changeHandler: Value => Unit) extends QLWidget {
  private val selectedValue: DoubleProperty = new DoubleProperty()
  private val slider = new Slider {
    min = min.doubleValue
    max = max.doubleValue
    value <==> selectedValue
  }

  selectedValue.onChange {
    val qlValue = NumericValue.doubleToNumericValue(selectedValue.value, numericType)
    this.setValue(qlValue)
    changeHandler(qlValue)
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

  override def getSFXNode: Node = slider
}
