package view

import ast.ExpressionNode.Env
import model.DisplayQuestion

import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class NumericQuestion(question: DisplayQuestion, env: Env) extends GUIQuestion {
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
