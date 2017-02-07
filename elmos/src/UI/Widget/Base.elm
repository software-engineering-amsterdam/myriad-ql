module UI.Widget.Base exposing (container)

import Html exposing (Html, div, label, text)
import Html.Attributes exposing (class, for)
import AST exposing (Field)


container : Field -> (Field -> Html msg) -> Html msg
container field f =
    div [ class "form-group" ]
        [ label [ for field.id ]
            [ text field.label ]
        , f field
        ]
