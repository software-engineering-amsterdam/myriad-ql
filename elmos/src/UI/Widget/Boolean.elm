module UI.Widget.Boolean exposing (view)

import Html exposing (Html, div, label, input)
import Html.Attributes exposing (type_, id, class, checked, disabled)
import Html.Events exposing (onCheck)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> Html msg
view { identifier, env, onChange, editable } =
    let
        isChecked =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asBool
                |> Maybe.withDefault False
    in
        div [ class "checkbox" ]
            [ label []
                [ input
                    [ type_ "checkbox"
                    , id identifier
                    , checked isChecked
                    , onCheck (Values.bool >> onChange)
                    , disabled (not editable)
                    ]
                    []
                ]
            ]
