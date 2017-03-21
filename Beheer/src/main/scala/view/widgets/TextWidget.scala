package view.widgets

import values.{ StringValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control.TextField

class TextWidget(width: Double, protected val changeHandler: Option[Value => Unit]) extends QLWidget {
  private val textField: TextField = new TextField()

  textField.setPrefWidth(width)
  textField.onAction = handle {
    changeHandler.foreach((f) => f(StringValue(textField.text.value)))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case StringValue(str) => textField.text.value_=(str)
    case UndefinedValue => textField.text.value_=("")
    case v => sys.error(s"Incompatible value $v for Text widget")
  }

  override def displayNode: Node = textField
}
