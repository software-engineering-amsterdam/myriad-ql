package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control.CheckBox

class CheckboxWidget(protected val changeHandler: Option[Value => Unit]) extends QLWidget {
  private val checkBox: CheckBox = new CheckBox {
    selected = false
  }

  checkBox.onAction = handle {
    changeHandler.foreach((f) => f(BooleanValue(checkBox.selected.value)))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case BooleanValue(b) => checkBox.selected = b
    // Checkbox is either checked or not, undefined becomes 'go to default value' in this case.
    case UndefinedValue => checkBox.selected = false
    case v => sys.error(s"Incompatible value $v for Checkbox widget")
  }

  override def displayNode: Node = checkBox
}
