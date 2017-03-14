module UI.Widget.BooleanRadio exposing (view)

import Html exposing (Html, div, label, input, text)
import Html.Attributes exposing (type_, class, checked)
import Html.Events exposing (onClick)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange } labels =
    let
        checkedIndexN =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asBool
                |> Maybe.map boolToIndex

        selections =
            [ ( checkedIndexN == Just 0, Values.bool True )
            , ( checkedIndexN == Just 1, Values.bool False )
            ]

        opts =
            List.map2 (,) labels selections
                |> List.map (\( optionLabel, ( selected, value ) ) -> renderOption optionLabel selected (onChange value))
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


boolToIndex : Bool -> Int
boolToIndex true =
    if true then
        0
    else
        1
