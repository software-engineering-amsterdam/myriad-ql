module UI.Widget.NumberParser exposing (parseIntegerInput, parseFloatInput)

import Maybe.Extra as Maybe
import QL.Values as Values exposing (Value, isValidInt, isValidFloat)


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.toMaybe
        >> Maybe.filter isValidInt
        >> Maybe.map Values.int
        >> Maybe.withDefault Values.undefined


parseFloatInput : String -> Value
parseFloatInput =
    String.toFloat
        >> Result.toMaybe
        >> Maybe.filter isValidFloat
        >> Maybe.map Values.float
        >> Maybe.withDefault Values.undefined
