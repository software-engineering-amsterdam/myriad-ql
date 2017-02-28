module QL.Parser exposing (parse)

import Combine exposing (InputStream)
import QL.Parser.Form as Form
import QL.AST exposing (Form)


parse : String -> Maybe Form
parse input =
    Combine.parse Form.form input
        |> Result.toMaybe
        |> Maybe.map asForm


asForm : ( (), InputStream, Form ) -> Form
asForm ( _, _, form ) =
    form
