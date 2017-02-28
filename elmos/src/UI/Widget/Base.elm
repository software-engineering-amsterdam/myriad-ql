module UI.Widget.Base exposing (WidgetContext, container)

import Html exposing (Html, div, label, text)
import Html.Attributes exposing (class, for)
import QL.Environment exposing (Environment)
import QL.Values exposing (Value)


type alias WidgetContext msg =
    { identifier : String
    , label : String
    , env : Environment
    , onChange : Value -> msg
    , editable : Bool
    }


type alias Widget msg =
    WidgetContext msg -> Html msg


container : WidgetContext msg -> Widget msg -> Html msg
container context widget =
    div [ class "form-group" ]
        [ label [ for context.identifier ]
            [ text context.label ]
        , widget context
        ]
