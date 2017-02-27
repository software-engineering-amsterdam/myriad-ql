module QL.Parser exposing (parse)

import Combine
import QL.Parser.Form exposing (form)
import QL.AST exposing (Form)
import Tuple3


parse : String -> Maybe Form
parse input =
    Combine.parse form input
        |> Result.toMaybe
        |> Maybe.map Tuple3.third
