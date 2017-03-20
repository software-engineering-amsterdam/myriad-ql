package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.collections.ObservableBuffer
import scalafx.scene.control.ChoiceBox

class DropDownWidget(trueText: String, falseText: String, width: Double)(implicit val changeHandler: Value => Unit) extends QLWidget {

  private val options = ObservableBuffer(trueText, falseText)
  private val dropDownBox = new ChoiceBox(options)
  dropDownBox.setPrefWidth(width)

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

  override def setValue(newVal: Value) = newVal match {
    case BooleanValue(true) => dropDownBox.selectionModel.select(trueText)
    case BooleanValue(false) => dropDownBox.selectionModel.select(falseText)
    case UndefinedValue => dropDownBox.selectionModel.select("")
    case v => sys.error(s"Incompatible value $v for Dropdown widget")
  }

  override def getSFXNode = dropDownBox

}
