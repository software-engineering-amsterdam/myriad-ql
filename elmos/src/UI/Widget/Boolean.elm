module UI.Widget.Boolean exposing (view)

import Html exposing (Html, div, label, input)
import Html.Attributes exposing (type_, id, class)
import AST exposing (Field)


view : Field -> Html msg
view field =
    div [ class "checkbox" ]
        [ label []
            [ input [ type_ "checkbox", id field.id ] [] ]
        ]
