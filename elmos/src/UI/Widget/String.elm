module UI.Widget.String exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, value, id, disabled)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values


view : WidgetContext msg -> Html msg
view { identifier, env, onChange, editable } =
    let
        textValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asString
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , id identifier
            , value textValue
            , disabled (not editable)
            , onInput (Values.string >> onChange)
            ]
            []
