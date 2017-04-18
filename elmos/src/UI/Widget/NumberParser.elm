module UI.Widget.NumberParser exposing (parseIntegerInput, parseDecimalInput)

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


parseDecimalInput : String -> Value
parseDecimalInput =
    String.toFloat
        >> Result.toMaybe
        >> Maybe.filter Numbers.isValidFloat
        >> Maybe.map Values.Decimal
        >> Maybe.withDefault Values.Undefined
