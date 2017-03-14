package view.widgets

import values.{ BooleanValue, Value }

import scalafx.beans.property.BooleanProperty
import scalafx.scene.control.CheckBox

class CheckboxWidget(implicit val changeHandler: Value => Unit) extends QLWidget[BooleanValue] {
  private val checkBoxValue = BooleanProperty(false)
  private val checkbox = new CheckBox {
    selected <==> checkBoxValue
  }

  checkBoxValue.onChange({
    changeHandler(BooleanValue(checkBoxValue.value))
  })

  override def setValue(newVal: BooleanValue) = {
    checkBoxValue <== BooleanProperty(newVal.value)
  }

  override def getSFXNode = checkbox
}
