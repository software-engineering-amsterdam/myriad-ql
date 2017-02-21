module QLS.Parser exposing (parse)

import Combine
import QLS.Parser.Stylesheet exposing (stylesheet)
import QLS.AST exposing (Stylesheet)
import Tuple3


parse : String -> Maybe Stylesheet
parse input =
    Combine.parse stylesheet input
        |> Result.toMaybe
        |> Maybe.map Tuple3.third
