package view.widgets

import values.{ BooleanValue, Value }

import scalafx.beans.property.BooleanProperty
import scalafx.scene.control.CheckBox

class CheckboxWidget(implicit val changeHandler: Value => Unit) extends QLWidget {
  private val checkBoxValue = BooleanProperty(false)
  private val checkbox = new CheckBox {
    selected <==> checkBoxValue
  }

  checkBoxValue.onChange({
    changeHandler(BooleanValue(checkBoxValue.value))
  })

  override def setValue(newVal: Value) = newVal match {
    case BooleanValue(b) => checkBoxValue <== BooleanProperty(b)
    // Value that we cannot handle, deselect (since it is definitely not true).
    case _ => checkBoxValue <== BooleanProperty(false)
  }

  override def getSFXNode = checkbox
}
