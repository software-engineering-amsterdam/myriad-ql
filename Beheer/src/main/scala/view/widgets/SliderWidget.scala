package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.scene.Node
import scalafx.scene.control.Slider

class SliderWidget(
  min: BigDecimal,
    max: BigDecimal,
    numericType: NumericType,
    protected val changeHandler: Option[Value => Unit]
) extends QLWidget {

  private val slider = new Slider()
  slider.min = min.doubleValue
  slider.max = max.doubleValue

  slider.value.onChange {
    val qlValue = NumericValue.doubleToNumericValue(slider.value.value, numericType)
    this.setValue(qlValue)
    changeHandler.foreach((f) => f(qlValue))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue =>
      if (n.value >= min && n.value <= max) {
        slider.value = n.value.doubleValue()
      } else if (n.value < min) {
        slider.value = min.doubleValue
      } else {
        slider.value = max.doubleValue
      }
    case UndefinedValue => slider.value = min.doubleValue
    case v => sys.error(s"Incompatible value $v for Slider Widget")
  }
  override def displayNode: Node = slider
}
