package view.widgets

import values.Value

import scalafx.scene.Node

trait QLWidget {
  protected val changeHandler: Option[Value => Unit]

  def displayNode: Node

  def setValue(newVal: Value): Unit
}
