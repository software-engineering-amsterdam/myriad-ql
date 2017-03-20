package view.widgets

import values.{ StringValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.control.TextField

class TextWidget(width: Double, changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {
  override val displayNode: TextField = new TextField()

  displayNode.setPrefWidth(width)
  displayNode.onAction = handle {
    super.handleUpdate(StringValue(displayNode.text.value))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case StringValue(str) => displayNode.text.value_=(str)
    case UndefinedValue => displayNode.text.value_=("")
    case v => sys.error(s"Incompatible value $v for Text widget")
  }
}
