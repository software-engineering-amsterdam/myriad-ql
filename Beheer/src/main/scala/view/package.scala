import values.Value

import scalafx.collections.ObservableMap

package object view {
  type ObservableEnv = ObservableMap[String, Value]

  var env: ObservableEnv = ObservableMap()

  def updateEnv(identifier: String, value: Value) = {
    env += (identifier -> value)
    println(env.toMap)
  }
}
