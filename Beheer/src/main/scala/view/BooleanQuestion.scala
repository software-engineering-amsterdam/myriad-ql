package view

import ast.{ Checkbox, Dropdown, QuestionStyle, Radio }
import model.DisplayQuestion
import values.{ BooleanValue, UndefinedValue }

import scalafx.collections.ObservableBuffer
import scalafx.scene.control._
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text

class BooleanQuestion(val question: DisplayQuestion, questionStyle: Option[QuestionStyle] = None) extends GUIQuestion {
  private val booleanToggle = new ToggleGroup {
    selectedToggle.onChange { (_, _, value) =>
      value.getUserData match {
        case b: BooleanValue => updateEnv(question.identifier, b)
        case _ => sys.error(s"Invalid value for BooleanQuestion ${question.identifier}")
      }
      ()
    }
  }

  private val checkbox = {
    val box = new CheckBox()
    box.onAction = { _ =>
      updateEnv(question.identifier, BooleanValue(box.selectedProperty().getValue))
    }
    Seq(box)
  }

  private def widget = questionStyle match {
    case Some(q) => q.widget match {
      case Some(w) => w.widgetType match {
        case Checkbox => checkbox
        case Radio(trueText, falseText) => radio(trueText, falseText)
        case Dropdown(trueText, falseText) => dropdown(trueText, falseText)
        case _ => sys.error(s"Invalid widget type $w for ${question.identifier}")
      }
      case None => defaultRadio
    }
    case None => defaultRadio
  }

  private def defaultRadio = radio("Yes", "No")

  private def radio(trueText: String, falseText: String) = Seq(
    new RadioButton {
      text = trueText
      toggleGroup = booleanToggle
      userData = BooleanValue(true)
    },
    new RadioButton {
      text = falseText
      toggleGroup = booleanToggle
      userData = BooleanValue(false)
    }
  )

  private def dropdown(trueText: String, falseText: String) = {
    val box = new ChoiceBox[String] {
      items = ObservableBuffer(Seq(trueText, falseText))
    }
    box.value.onChange {
      if (box.value.value == trueText)
        updateEnv(question.identifier, BooleanValue(true))
      else if (box.value.value == falseText)
        updateEnv(question.identifier, BooleanValue(false))
      else
        updateEnv(question.identifier, UndefinedValue)
    }
    Seq(box)
  }

  displayBox.children = Seq(
    new Text(question.label),
    new HBox {
      children = widget
    }
  )
}
