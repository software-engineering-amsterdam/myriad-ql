module UI.Widget.Values exposing (parseIntegerInput)

import QL.Values as Values exposing (Value)


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.map Values.int
        >> Result.withDefault Values.undefined
