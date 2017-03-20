package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.beans.property.BooleanProperty
import scalafx.scene.control.CheckBox

class CheckboxWidget(changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {
  private val checkBoxValue = BooleanProperty(false)

  checkBoxValue.onChange {
    super.handleUpdate(BooleanValue(checkBoxValue.value))
  }

  override val displayNode: CheckBox = new CheckBox {
    selected <==> checkBoxValue
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case BooleanValue(b) => checkBoxValue.value_=(b)
    // Checkbox is either checked or not, undefined becomes 'go to default value' in this case.
    case UndefinedValue => checkBoxValue.value_=(false)
    case v => sys.error(s"Incompatible value $v for Checkbox widget")
  }
}
