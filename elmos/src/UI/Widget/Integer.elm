module UI.Widget.Integer exposing (..)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, value, id)
import UI.Widget.Base exposing (WidgetContext)
import UI.FormData as FormData


view : WidgetContext -> Html msg
view { field, formData } =
    let
        textValue =
            FormData.getInteger field.id formData
                |> Maybe.map toString
                |> Maybe.withDefault ""
    in
        input
            [ type_ "text"
            , class "form-control"
            , value textValue
            , id field.id
            ]
            []
