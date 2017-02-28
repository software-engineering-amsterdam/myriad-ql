module UI.Messages exposing (error, warning, location, locations, varName, ids)

import QL.AST exposing (Location(Location), Id)
import Html exposing (Html, div, text, b, span)
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
locations locs =
    span [] (List.map location locs |> List.intersperse (text ", "))


varName : String -> Html msg
varName name =
    b [] [ text name ]


locationToString : Location -> String
locationToString (Location line col) =
    "line " ++ toString line ++ " ( col " ++ toString col ++ " )"


id : Id -> Html msg
id ( name, loc ) =
    span [] [ varName name, text " at ", location loc ]


ids : List Id -> Html msg
ids idList =
    span [] (List.map id idList |> List.intersperse (text ", "))
