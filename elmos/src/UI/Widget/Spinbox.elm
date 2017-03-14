module UI.Widget.Spinbox exposing (..)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, step, defaultValue, id, disabled)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)
import UI.Widget.Values as Values


view : WidgetContext msg -> Html msg
view { identifier, env, onChange, editable } =
    let
        currentValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asInt
    in
        input
            [ type_ "number"
            , class "form-control"
            , id identifier
            , step "1"
            , defaultValue (Maybe.withDefault "" <| Maybe.map toString currentValue)
            , onInput (Values.parseIntegerInput >> onChange)
            , disabled (not editable)
            ]
            []
