module UI.Widget.StringRadio exposing (view)

import Html exposing (Html, div, label, input, text)
import Html.Attributes exposing (type_, class, checked, disabled)
import Html.Events exposing (onClick)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange, editable } values =
    let
        selectedValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asString
    in
        div []
            (List.map
                (\value -> renderOption editable value (Just value == selectedValue) onChange)
                values
            )


renderOption : Bool -> String -> Bool -> (Value -> msg) -> Html msg
renderOption editable value isSelected onChange =
    div [ class "radio" ]
        [ label []
            [ input
                [ type_ "radio"
                , checked isSelected
                , disabled (not editable)
                , onClick (onChange (Values.string value))
                ]
                []
            , text value
            ]
        ]
