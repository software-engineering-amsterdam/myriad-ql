module UI.Widget.String exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, value, id)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import Environment
import Values exposing (Value(Str))


view : WidgetContext msg -> Html msg
view { identifier, formData, onChange } =
    let
        textValue =
            FormData.getString identifier formData
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , id identifier
            , value textValue
            , onInput (Str >> onChange)
            ]
            []
