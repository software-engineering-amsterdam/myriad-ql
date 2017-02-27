package view

import ast.ExpressionNode.Env
import model.DisplayQuestion
import values.BooleanValue

import scalafx.scene.control.{ RadioButton, ToggleGroup }
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class BooleanQuestion(question: DisplayQuestion, env: Env) extends GUIQuestion {
  val element = new HBox {
    private val boolean = new ToggleGroup {
      selectedToggle.onChange { (_, _, value) =>
        println(s"${question.identifier}: ${value.getUserData}")
        value.getUserData match {
          case b: BooleanValue => GUI.updateEnv(question.identifier, b)
          case _ => sys.error("Invalid value for BooleanQuestion")
        }
      }
    }

    children = Seq(
      new Text(question.label),
      new RadioButton {
        text = "Yes"
        toggleGroup = boolean
        userData = BooleanValue(true)
      },
      new RadioButton {
        text = "No"
        toggleGroup = boolean
        userData = BooleanValue(false)
      }
    )
    visible = question.show(env)
  }
}
