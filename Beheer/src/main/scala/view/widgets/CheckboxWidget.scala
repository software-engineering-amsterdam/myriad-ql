package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.beans.property.BooleanProperty
import scalafx.scene.control.CheckBox

class CheckboxWidget(implicit val changeHandler: Value => Unit) extends QLWidget {
  private val checkBoxValue = BooleanProperty(false)
  private val checkbox = new CheckBox {
    selected <==> checkBoxValue
  }

  checkBoxValue.onChange(changeHandler(BooleanValue(checkBoxValue.value)))

  override def setValue(newVal: Value) = newVal match {
    case BooleanValue(b) => checkBoxValue.value_=(b)
    // Checkbox is either checked or not, undefined becomes 'go to default value' in this case.
    case UndefinedValue => checkBoxValue.value_=(false)
    case v => sys.error(s"Incompatible value $v for Checkbox widget")
  }

  override def getSFXNode = checkbox
}
