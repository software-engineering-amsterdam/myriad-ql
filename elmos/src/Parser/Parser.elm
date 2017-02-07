module Parser.Parser exposing (parse)

import Combine
import Parser.Form exposing (form)
import AST exposing (Form)
import Tuple3


parse : String -> Maybe Form
parse input =
    Combine.parse form input
        |> Result.toMaybe
        |> Maybe.map Tuple3.third
