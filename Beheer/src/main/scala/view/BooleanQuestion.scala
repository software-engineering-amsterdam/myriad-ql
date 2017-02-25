package view

import model.DisplayQuestion
import values.Value

import scalafx.scene.control.CheckBox
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class BooleanQuestion(question: DisplayQuestion, env: Map[String, Value]) extends GUIQuestion {
  val element = new HBox {
    children = Seq(
      new Text(question.label),
      new CheckBox()
    )
    visible = question.show(env)
  }
}
