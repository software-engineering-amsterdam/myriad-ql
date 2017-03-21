import values.{ UndefinedValue, Value }

import scalafx.collections.ObservableMap

package object view {
  type ObservableEnv = ObservableMap[String, Value]

  var env: ObservableEnv = ObservableMap()

  def updateEnv(identifier: String)(value: Value) = {
    println(s"Updating env, identifier: $identifier, old value: ${env.getOrElse(identifier, UndefinedValue)} value: $value")
    env += (identifier -> value)
    println(env.toMap)
  }
}
