package view

import model.DisplayQuestion
import values.MoneyValue

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.TextField
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class NumericQuestion(question: DisplayQuestion) extends GUIQuestion {
  val textField = new TextField {
    maxWidth = 200
    onAction = (a: ActionEvent) => {
      val s = this.delegate.text.value.toDouble
      updateEnv(question.identifier, MoneyValue(s))
    }
  }

  val element = new HBox {
    children = Seq(
      new Text(question.label),
      textField
    )
    disable = isDisabled(question)
    visible <== isVisible(question)
  }
}
