module UI.Widget.String exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class)
import UI.Widget.Base exposing (WidgetContext)


view : WidgetContext -> Html msg
view field =
    input [ type_ "text", class "form-control" ] []
