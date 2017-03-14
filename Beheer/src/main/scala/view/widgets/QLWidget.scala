package view.widgets

import values.Value

import scalafx.scene.Node

trait QLWidget[T] {
  val changeHandler: Value => Unit

  def setValue(newVal: T): Unit

  def getSFXNode: Node
}

