module UI.Widget.Base exposing (WidgetContext, container)

import Html exposing (Html, div, label, text)
import Html.Attributes exposing (class, for)
import AST exposing (Field)
import UI.FormData exposing (FormData)
import Values exposing (Value)


type alias WidgetContext msg =
    { field : Field
    , formData : FormData
    , onChange : Value -> msg
    }


type alias Widget msg =
    WidgetContext msg -> Html msg


container : WidgetContext msg -> Widget msg -> Html msg
container context widget =
    div [ class "form-group" ]
        [ label [ for context.field.id ]
            [ text context.field.label ]
        , widget context
        ]
