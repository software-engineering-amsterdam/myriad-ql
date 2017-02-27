import javafx.beans.binding.{ BooleanBinding, StringBinding }

import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.{ NumericValue, Value }

import scalafx.collections.ObservableMap

package object view {
  type ObservableEnv = ObservableMap[String, Value]

  var env: ObservableEnv = ObservableMap()

  def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  def isVisible(question: DisplayQuestion): BooleanBinding = new BooleanBinding {
    bind(env)

    override def computeValue: Boolean = question.show(env.toMap)
  }

  def computeValue(question: DisplayQuestion): StringBinding = new StringBinding {
    bind(env)

    override def computeValue = {
      println(question.identifier)
      question match {
        case ComputedQuestion(_, _, _, _, e) =>
          println(env)
          println(e.value(env.toMap))
          e.value(env.toMap).toString
        case _ => ""
      }
    }
  }

  def updateEnv(identifier: String, value: Value) = env += (identifier -> value)
}
