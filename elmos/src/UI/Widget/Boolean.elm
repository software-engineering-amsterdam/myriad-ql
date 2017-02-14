module UI.Widget.Boolean exposing (view)

import Html exposing (Html, div, label, input)
import Html.Attributes exposing (type_, id, class, checked)
import Html.Events exposing (onCheck)
import Environment
import UI.Widget.Base exposing (WidgetContext)
import Values exposing (Value(Boolean))


view : WidgetContext msg -> Html msg
view { identifier, formData, onChange } =
    let
        isChecked =
            FormData.getBoolean identifier formData
                |> Maybe.withDefault False
    in
        div [ class "checkbox" ]
            [ label []
                [ input
                    [ type_ "checkbox"
                    , id identifier
                    , checked isChecked
                    , onCheck (Boolean >> onChange)
                    ]
                    []
                ]
            ]
