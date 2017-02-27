package model

import ast.{ ExpressionNode, Type }
import values.{ BooleanValue, Value }

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayCondition: Iterable[ExpressionNode]
  val `type`: Type

  def show(env: Map[String, Value]): Boolean = {
    def show(env: Map[String, Value], conditions: Iterable[ExpressionNode]): Boolean = {
      conditions match {
        case Nil => true
        case head :: tail =>
          val headValue = head.value(env) match {
            case BooleanValue(b) => b
            case _ => false
          }
          headValue && show(env, tail)
      }
    }

    show(env, displayCondition)
  }
}

case class OpenQuestion(identifier: String, label: String, `type`: Type, displayCondition: Iterable[ExpressionNode]) extends DisplayQuestion

case class ComputedQuestion(identifier: String, label: String, `type`: Type, displayCondition: Iterable[ExpressionNode], value: ExpressionNode) extends DisplayQuestion
