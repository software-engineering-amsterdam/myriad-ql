module UI.Widget.BooleanRadio exposing (view)

import Html exposing (Html, div, label, input, text)
import Html.Attributes exposing (type_, class, checked)
import Html.Events exposing (onClick)
import List.Extra as List
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange } optionLabels =
    let
        selectedValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asBool

        selections =
            [ ( selectedValue == Just True, Values.bool True )
            , ( selectedValue == Just False, Values.bool False )
            ]

        opts =
            List.zip optionLabels selections
                |> List.map (\( optionLabel, ( isSelected, value ) ) -> renderOption optionLabel isSelected (onChange value))
    in
        div []
            opts


renderOption : String -> Bool -> msg -> Html msg
renderOption optionLabel isSelected onChange =
    div [ class "radio" ]
        [ label []
            [ input
                [ type_ "radio"
                , checked isSelected
                , onClick onChange
                ]
                []
            , text optionLabel
            ]
        ]
