package view.widgets

import values.Value

import scalafx.scene.Node

trait QLWidget {
  val changeHandler: Value => Unit

  def setValue(newVal: Value): Unit

  def getSFXNode: Node
}

