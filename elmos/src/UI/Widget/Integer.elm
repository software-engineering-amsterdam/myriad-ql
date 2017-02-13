module UI.Widget.Integer exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, defaultValue, id)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import UI.FormData as FormData exposing (FormValue(Integer, Undefined))


view : WidgetContext msg -> Html msg
view { field, formData, onChange } =
    let
        textValue =
            FormData.getInteger field.id formData
                |> Maybe.map toString
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , defaultValue textValue
            , id field.id
            , onInput (parseIntegerInput >> onChange)
            ]
            []


parseIntegerInput : String -> FormValue
parseIntegerInput input =
    String.toInt input
        |> Result.toMaybe
        |> Maybe.map Integer
        |> Maybe.withDefault Undefined
