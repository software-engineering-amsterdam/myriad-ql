package view.widgets

import values.{ BooleanValue, UndefinedValue, Value }

import scalafx.collections.ObservableBuffer
import scalafx.scene.Node
import scalafx.scene.control.ChoiceBox

class DropDownWidget(
  trueText: String,
    falseText: String,
    width: Double,
    protected val changeHandler: Option[Value => Unit]
) extends QLWidget {

  private val choiceBox: ChoiceBox[String] = new ChoiceBox(ObservableBuffer(trueText, falseText))
  choiceBox.setPrefWidth(width)

  choiceBox.value.onChange {
    val newVal = choiceBox.value.value
    if (newVal == trueText) {
      handleChange(BooleanValue(true))
    } else if (newVal == falseText) {
      handleChange(BooleanValue(false))
    } else {
      handleChange(UndefinedValue)
    }
  }

  private def handleChange(value: Value): Unit = changeHandler.foreach((f) => f(value))

  override def setValue(newVal: Value): Unit = newVal match {
    case BooleanValue(true) => choiceBox.selectionModel.select(trueText)
    case BooleanValue(false) => choiceBox.selectionModel.select(falseText)
    case UndefinedValue => choiceBox.selectionModel.select("")
    case v => sys.error(s"Incompatible value $v for Dropdown widget")
  }

  override def displayNode: Node = choiceBox
}
