module UI.Widget.NumberParser exposing (parseIntegerInput, parseFloatInput)

import Maybe.Extra as Maybe
import QL.Values as Values exposing (Value, isValidInt, isValidFloat)


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.toMaybe
        >> Maybe.filter isValidInt
        >> Maybe.map Values.Integer
        >> Maybe.withDefault Values.Undefined


parseFloatInput : String -> Value
parseFloatInput =
    String.toFloat
        >> Result.toMaybe
        >> Maybe.filter isValidFloat
        >> Maybe.map Values.Decimal
        >> Maybe.withDefault Values.Undefined
