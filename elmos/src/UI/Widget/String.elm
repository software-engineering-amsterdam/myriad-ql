module UI.Widget.String exposing (view)

import Html exposing (Html, input)
import Html.Attributes exposing (type_, class, value, id)
import UI.Widget.Base exposing (WidgetContext)
import UI.FormData as FormData


view : WidgetContext -> Html msg
view { field, formData } =
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
            ]
            []
