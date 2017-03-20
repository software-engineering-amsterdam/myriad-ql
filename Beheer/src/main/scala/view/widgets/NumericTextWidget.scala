package view.widgets

import ast.NumericType
import values.{ NumericValue, UndefinedValue, Value }

import scalafx.Includes._
import scalafx.scene.control.TextField

class NumericTextWidget(width: Double, numberType: NumericType, changeHandler: Option[Value => Unit]) extends QLWidget(changeHandler) {

  override val displayNode: TextField = new TextField()
  displayNode.setPrefWidth(width)

  displayNode.onAction = handle {
    val qlValue = NumericValue.stringToNumericValue(displayNode.text.value, numberType)
    this.setValue(qlValue)
    super.handleUpdate(qlValue)
  }

  override def setValue(newVal: Value): Unit = newVal match {
    case n: NumericValue => displayNode.text.value = NumericValue.upgradeNumericToType(n, numberType).toString
    case UndefinedValue => displayNode.text.value = ""
    case v => sys.error(s"Incompatible value $v for NumericText widget")
  }

}
