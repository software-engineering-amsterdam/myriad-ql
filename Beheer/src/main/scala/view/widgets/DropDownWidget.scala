package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.collections.ObservableBuffer
import scalafx.scene.control.ChoiceBox

class DropDownWidget(trueText: String, falseText: String, width: Double, changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {
  override val displayNode: ChoiceBox[String] = new ChoiceBox(ObservableBuffer(trueText, falseText))
  displayNode.setPrefWidth(width)

  displayNode.value.onChange {
    val newVal = displayNode.value.value
    if (newVal == trueText) {
      super.handleUpdate(BooleanValue(true))
    } else if (newVal == falseText) {
      super.handleUpdate(BooleanValue(false))
    } else {
      super.handleUpdate(UndefinedValue)
    }
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case BooleanValue(true) => displayNode.selectionModel.select(trueText)
    case BooleanValue(false) => displayNode.selectionModel.select(falseText)
    case UndefinedValue => displayNode.selectionModel.select("")
    case v => sys.error(s"Incompatible value $v for Dropdown widget")
  }

}
