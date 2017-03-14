package view.widgets

import values.{ BooleanValue, Value }

import scalafx.scene.Node
import scalafx.scene.control.{ RadioButton, ToggleGroup }
import scalafx.scene.layout.HBox

class RadioWidget(trueText: String, falseText: String)(implicit val changeHandler: Value => Unit) extends QLWidget[BooleanValue] {
  private val toggle = new ToggleGroup()
  private val trueButton = new RadioButton {
    text = trueText
    toggleGroup = toggle
    userData = BooleanValue(true) //Must be object (child of AnyRef) due to javafx typing stuff.
  }
  private val falseButton = new RadioButton {
    text = falseText
    toggleGroup = toggle
    userData = BooleanValue(false)
  }

  toggle.selectedToggle.onChange({
    toggle.selectedToggle.value.getUserData match {
      case b: BooleanValue => changeHandler(b)
      case _ => sys.error("Wrong type in radiobutton userdata field.")
    }
  })

  override def setValue(newVal: BooleanValue): Unit = {
    if (newVal.value) {
      trueButton.selected = true
    } else {
      falseButton.selected = true
    }
  }

  override def getSFXNode: Node = new HBox {
    children = Seq(trueButton, falseButton)
  }
}
