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
    in
        div []
            (List.indexedMap
                (\index label -> renderOption index label (Maybe.map ((==) index) checkedIndexN) onChange)
                labels
            )


renderOption : Int -> String -> Maybe Bool -> (Value -> msg) -> Html msg
renderOption index optionLabel isChecked onChange =
    div [ class "radio" ]
        [ label []
            [ input
                [ type_ "radio"
                , checked (Maybe.withDefault False isChecked)
                , onClick (onChange (Values.bool (indexToBool index)))
                ]
                []
            , text optionLabel
            ]
        ]


indexToBool : Int -> Bool
indexToBool index =
    index == 0


boolToIndex : Bool -> Int
boolToIndex true =
    if true then
        0
    else
        1
