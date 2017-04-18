package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.control.TextField

class NumericTextWidget(
    width: Double,
    numberType: NumericType,
    protected val changeHandler: Option[Value => Unit]
) extends QLWidget {

  private val textfield: TextField = new TextField()
  textfield.setPrefWidth(width)

  textfield.onAction = handle {
    val qlValue = NumericValue.stringToNumericValue(textfield.text.value, numberType)
    setValue(qlValue)
    changeHandler.foreach((f) => f(qlValue))
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => textfield.text = NumericValue.upgradeNumericToType(n, numberType).toString
    case UndefinedValue => textfield.text = ""
    case v => sys.error(s"Incompatible value $v for NumericText widget")
  }

  override def displayNode: Node = textfield
}
