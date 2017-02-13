module UI.Widget.Boolean exposing (view)

import Html exposing (Html, div, label, input)
import Html.Attributes exposing (type_, id, class, checked)
import UI.FormData as FormData
import UI.Widget.Base exposing (WidgetContext)


view : WidgetContext -> Html msg
view { field, formData } =
    let
        isChecked =
            FormData.getBoolean field.id formData
                |> Maybe.withDefault False
    in
        div [ class "checkbox" ]
            [ label []
                [ input
                    [ type_ "checkbox"
                    , id field.id
                    , checked isChecked
                    ]
                    []
                ]
            ]
