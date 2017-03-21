module UI.Widget.NumberParser exposing (parseIntegerInput, parseFloatInput)

import Maybe.Extra as Maybe
import QL.Values as Values exposing (Value)
import QL.Numbers as Numbers


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.toMaybe
        >> Maybe.filter Numbers.isValidInt
        >> Maybe.map Values.Integer
        >> Maybe.withDefault Values.Undefined


parseFloatInput : String -> Value
parseFloatInput =
    String.toFloat
        >> Result.toMaybe
        >> Maybe.filter Numbers.isValidFloat
        >> Maybe.map Values.Decimal
        >> Maybe.withDefault Values.Undefined
