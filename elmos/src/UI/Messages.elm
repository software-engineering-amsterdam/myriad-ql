module UI.Messages exposing (error, warning, location, locations, varName)

import QL.AST exposing (Location(Location))
import Html exposing (Html, div, text, b)
import Html.Attributes exposing (class)


error : List (Html msg) -> Html msg
error =
    div [ class "alert", class "alert-danger" ]


warning : List (Html msg) -> Html msg
warning =
    div [ class "alert", class "alert-warning" ]


location : Location -> Html msg
location location =
    b [] [ text <| locationToString location ]


locations : List Location -> Html msg
locations locations =
    b []
        [ List.map locationToString locations
            |> String.join ", "
            |> text
        ]


varName : String -> Html msg
varName name =
    b [] [ text name ]


locationToString (Location line col) =
    "line " ++ toString line ++ " ( col " ++ toString col ++ " )"
