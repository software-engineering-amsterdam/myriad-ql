package view.widgets

import values.Value

import scalafx.scene.Node

abstract class QLWidget(changeHandler: Option[Value => Unit]) {
  val displayNode: Node

  def setValue(newVal: Value): Unit

  protected def handleUpdate(newVal: Value) = changeHandler.foreach(f => f(newVal))
}
