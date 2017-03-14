module UI.Widget.StringRadio exposing (view)

import Html exposing (Html, div, label, input, text)
import Html.Attributes exposing (type_, class, checked)
import Html.Events exposing (onClick)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange } values =
    let
        selectedLabel =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asString
    in
        div []
            (List.map
                (\value -> renderOption value (Just value == selectedLabel) onChange)
                values
            )


renderOption : String -> Bool -> (Value -> msg) -> Html msg
renderOption value isSelected onChange =
    div [ class "radio" ]
        [ label []
            [ input
                [ type_ "radio"
                , checked isSelected
                , onClick (onChange (Values.string value))
                ]
                []
            , text value
            ]
        ]
