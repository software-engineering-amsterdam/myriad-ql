module UI.Widget.Base exposing (WidgetContext, container)

import Html exposing (Html, label, text)
import Html.Keyed exposing (node)
import Html.Attributes exposing (class, for, style)
import QL.Environment exposing (Environment)
import QL.Values exposing (Value)


type alias WidgetContext msg =
    { identifier : String
    , label : String
    , env : Environment
    , onChange : Value -> msg
    , editable : Bool
    , style : List ( String, String )
    }


type alias Widget msg =
    WidgetContext msg -> Html msg


container : WidgetContext msg -> Widget msg -> Html msg
container context widget =
    node "div"
        [ class "form-group", style context.style ]
        [ ( context.identifier ++ "-label"
          , label [ for context.identifier ]
                [ text context.label ]
          )
        , ( context.identifier ++ "-body"
          , widget context
          )
        ]
