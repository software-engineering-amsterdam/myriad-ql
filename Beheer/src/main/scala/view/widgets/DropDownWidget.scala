package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.collections.ObservableBuffer
import scalafx.scene.control.ChoiceBox

class DropDownWidget(trueText: String, falseText: String)(implicit val changeHandler: Value => Unit) extends QLWidget[BooleanValue] {

  private val options = ObservableBuffer(trueText, falseText)
  private val dropDownBox = new ChoiceBox(options)

  dropDownBox.value.onChange {
    val newVal = dropDownBox.value.value
    if (newVal == trueText) {
      changeHandler(BooleanValue(true))
    } else if (newVal == falseText) {
      changeHandler(BooleanValue(false))
    } else {
      changeHandler(UndefinedValue)
    }
  }

  override def setValue(newVal: BooleanValue) =
    if (newVal.value) {
      dropDownBox.selectionModel.select(trueText)
    } else {
      dropDownBox.selectionModel.select(falseText)
    }

  override def getSFXNode = dropDownBox

}
