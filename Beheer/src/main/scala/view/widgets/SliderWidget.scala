package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.beans.property.DoubleProperty
import scalafx.scene.Node
import scalafx.scene.control.Slider

class SliderWidget(rangeStart: BigDecimal, rangeEnd: BigDecimal, numberType: NumericType)(implicit val changeHandler: Value => Unit) extends QLWidget {
  private val selectedValue: DoubleProperty = new DoubleProperty()
  private val slider = new Slider {
    min = rangeStart.doubleValue
    max = rangeEnd.doubleValue
    value <==> selectedValue
  }

  selectedValue.onChange {
    val qlValue = NumericValue.doubleToNumericValue(selectedValue.value, numberType)
    this.setValue(qlValue)
    changeHandler(qlValue)
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => {
      if (n.value >= rangeStart && n.value <= rangeEnd) {
        selectedValue.value_=(n.value)
      } else if (n.value < rangeStart) {
        selectedValue.value_=(rangeStart)
      } else { //n.value > rangeEnd
        selectedValue.value_=(rangeEnd)
      }
    }
    case UndefinedValue => selectedValue.value_=(rangeStart)
    case v => sys.error(s"Incompatible value $v for Slider Widget")
  }

  override def getSFXNode: Node = slider
}
