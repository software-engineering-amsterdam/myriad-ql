package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control.TextField

class NumericTextWidget(width: Double, numberType: NumericType)(implicit val changeHandler: Value => Unit) extends QLWidget {

  private val textfield = new TextField()
  textfield.setPrefWidth(width)

  textfield.onAction = handle {
    val qlValue = NumericValue.stringToNumericValue(textfield.text.value, numberType)
    this.setValue(qlValue)
    changeHandler(qlValue)
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => textfield.text.value = NumericValue.upgradeNumericToType(n, numberType).toString
    case UndefinedValue => textfield.text.value = ""
    case v => sys.error(s"Incompatible value $v for NumericText widget")
  }

  override def getSFXNode: Node = textfield
}
