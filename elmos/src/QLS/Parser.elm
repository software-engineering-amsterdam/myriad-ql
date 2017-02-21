module QLS.Parser exposing (parse)

import Combine
import QLS.Parser.StyleSheet exposing (styleSheet)
import QLS.AST exposing (StyleSheet)
import Tuple3


parse : String -> Maybe StyleSheet
parse input =
    Combine.parse styleSheet input
        |> Result.toMaybe
        |> Maybe.map Tuple3.third
