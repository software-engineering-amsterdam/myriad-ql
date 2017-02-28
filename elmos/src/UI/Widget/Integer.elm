module UI.Widget.Integer exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, defaultValue, id, disabled)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> Html msg
view { identifier, env, onChange, editable } =
    let
        textValue =
            Environment.getInteger identifier env
                |> Maybe.map toString
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , defaultValue textValue
            , id identifier
            , disabled (not editable)
            , onInput (parseIntegerInput >> onChange)
            ]
            []


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.map Values.int
        >> Result.withDefault Values.undefined
