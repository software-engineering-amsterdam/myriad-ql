module UI.Widget.Integer exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, defaultValue, id)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import Environment
import Values exposing (Value(Integer, Undefined))


view : WidgetContext msg -> Html msg
view { identifier, formData, onChange } =
    let
        textValue =
            FormData.getInteger identifier formData
                |> Maybe.map toString
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , defaultValue textValue
            , id identifier
            , onInput (parseIntegerInput >> onChange)
            ]
            []


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.toMaybe
        >> Maybe.map Integer
        >> Maybe.withDefault Undefined
