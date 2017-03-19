package model

import ast.{ ExpressionNode, Type }
import values.Evaluator.Env
import values.{ BooleanValue, Evaluator }

sealed trait DisplayQuestion {
  val identifier: String
  val label: String
  val displayCondition: Iterable[ExpressionNode]
  val `type`: Type

  def show(env: Env): Boolean = {
    def show(env: Env, conditions: Iterable[ExpressionNode]): Boolean = {
      conditions match {
        case Nil => true
        case head :: tail =>
          val headValue = Evaluator(env, head) match {
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
