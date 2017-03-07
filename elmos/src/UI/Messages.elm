module UI.Messages exposing (error, warning, renderLocation, renderLocations, renderVarName, renderId, renderIds, renderType)

import QL.AST exposing (Id, Location(Location), ValueType)
import Html exposing (Html, div, text, b, span)
import Html.Attributes exposing (class)


error : List (Html msg) -> Html msg
error =
    div [ class "alert", class "alert-danger" ]


warning : List (Html msg) -> Html msg
warning =
    div [ class "alert", class "alert-warning" ]


renderLocation : Location -> Html msg
renderLocation location =
    b [] [ text <| locationToString location ]


renderLocations : List Location -> Html msg
renderLocations locs =
    span [] (List.map renderLocation locs |> List.intersperse (text ", "))


renderVarName : String -> Html msg
renderVarName name =
    b [] [ text name ]


locationToString : Location -> String
locationToString (Location line col) =
    "line " ++ toString line ++ " ( col " ++ toString col ++ " )"


renderId : Id -> Html msg
renderId ( name, loc ) =
    span [] [ renderVarName name, text " at ", renderLocation loc ]


renderIds : List Id -> Html msg
renderIds idList =
    span [] (List.map renderId idList |> List.intersperse (text ", "))


renderType : ValueType -> Html msg
renderType valueType =
    b [] [ text <| toString valueType ]
