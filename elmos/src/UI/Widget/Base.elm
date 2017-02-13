module UI.Widget.Base exposing (WidgetContext, container)

import Html exposing (Html, div, label, text)
import Html.Attributes exposing (class, for)
import AST exposing (Field)
import UI.FormData exposing (FormData)


type alias WidgetContext =
    { field : Field
    , formData : FormData
    }


type alias Widget msg =
    WidgetContext -> Html msg


container : WidgetContext -> Widget msg -> Html msg
container context widget =
    div [ class "form-group" ]
        [ label [ for context.field.id ]
            [ text context.field.label ]
        , widget context
        ]
