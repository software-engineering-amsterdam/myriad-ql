package view

import javafx.beans.binding.StringBinding

import scalafx.Includes._
import scalafx.application.{ JFXApp, Platform }
import scalafx.event.ActionEvent
import scalafx.geometry.{ HPos, Insets }
import scalafx.scene.Scene
import scalafx.scene.control.{ Button, ComboBox, Label, TextField }
import scalafx.scene.layout.{ ColumnConstraints, GridPane, Priority }

trait UnitConverter {
  val description: String

  def run(input: String): String

  override def toString = description
}

class UnitConverters(converters: UnitConverter*) {
  val available = List(converters: _*)
}

object MMtoInches extends UnitConverter {
  val description: String = "Millimeters to inches"

  def run(input: String): String = try {
    (input.toDouble / 25.4).toString
  } catch {
    case ex: Throwable => ex.toString
  }
}

object InchesToMM extends UnitConverter {
  val description: String = "Inches to millimeters"

  def run(input: String): String = try {
    (input.toDouble * 25.4).toString
  } catch {
    case ex: Throwable => ex.toString
  }
}

class PureScalaFXView(converters: UnitConverters) extends JFXApp.PrimaryStage {

  // UI Definition
  title = "Unit conversion"

  private val types = new ComboBox[UnitConverter]() {
    maxWidth = Double.MaxValue
    margin = Insets(3)
  }

  private val from = new TextField {
    margin = Insets(3)
    prefWidth = 200.0
  }

  private val to = new TextField {
    prefWidth = 200.0
    margin = Insets(3)
    editable = false
  }

  scene = new Scene {
    content = new GridPane {
      padding = Insets(5)

      add(new Label("Conversion type:"), 0, 0)
      add(new Label("From:"), 0, 1)
      add(new Label("To:"), 0, 2)

      add(types, 1, 0)
      add(from, 1, 1)
      add(to, 1, 2)

      add(new Button("Close") {
        // inline event handler binding
        onAction = (e: ActionEvent) => Platform.exit()
      }, 1, 3)

      columnConstraints = List(
        new ColumnConstraints {
          halignment = HPos.Left
          hgrow = Priority.Sometimes
          margin = Insets(5)
        },
        new ColumnConstraints {
          halignment = HPos.Right
          hgrow = Priority.Always
          margin = Insets(5)
        }
      )
    }
  }

  // Filling the combo box
  for (converter <- converters.available) {
    types += converter
  }
  types.getSelectionModel.selectFirst()

  // Data binding
  to.text <== new StringBinding {
    bind(from.text.delegate, types.getSelectionModel.selectedItemProperty)

    def computeValue() = types.getSelectionModel.getSelectedItem.run(from.text.value)
  }
}

object PureScalaFX extends JFXApp {

  stage = new PureScalaFXView(new UnitConverters(InchesToMM, MMtoInches))

}