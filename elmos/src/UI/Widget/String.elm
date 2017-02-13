module UI.Widget.String exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, value, id)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import UI.FormData as FormData exposing (FormValue(Str))


view : WidgetContext msg -> Html msg
view { field, formData, onChange } =
    let
        textValue =
            FormData.getString field.id formData
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , id field.id
            , value textValue
            , onInput (Str >> onChange)
            ]
            []
