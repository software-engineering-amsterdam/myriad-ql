package view

import model.DisplayQuestion
import values.Value

import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class DateQuestion(question: DisplayQuestion, env: Map[String, Value]) extends GUIQuestion {
  val element = new HBox {
    children = Seq(
      new Text(question.label),
      new TextField {
        maxWidth = 200
      }
    )
    visible = question.show(env)
  }
}
