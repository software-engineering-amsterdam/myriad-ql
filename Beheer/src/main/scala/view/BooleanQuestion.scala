package view

import model.DisplayQuestion
import values.BooleanValue

import scalafx.scene.control.{ RadioButton, ToggleGroup }
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class BooleanQuestion(question: DisplayQuestion) extends GUIQuestion {
  val element = new HBox {
    private val booleanToggle = new ToggleGroup {
      selectedToggle.onChange { (_, _, value) =>
        value.getUserData match {
          case b: BooleanValue => updateEnv(question.identifier, b)
          case _ => sys.error("Invalid value for BooleanQuestion")
        }
      }
    }

    children = Seq(
      new Text(question.label),
      new RadioButton {
        text = "Yes"
        toggleGroup = booleanToggle
        userData = BooleanValue(true)
      },
      new RadioButton {
        text = "No"
        toggleGroup = booleanToggle
        userData = BooleanValue(false)
      }
    )
    disable = isDisabled(question)
    visible <== isVisible(question)
  }
}
