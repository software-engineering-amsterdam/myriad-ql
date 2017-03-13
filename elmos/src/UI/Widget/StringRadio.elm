module UI.Widget.StringRadio exposing (view)

import Html exposing (Html, div, label, input, text)
import Html.Attributes exposing (type_, class, checked)
import Html.Events exposing (onClick)
import List.Extra as List
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange } values =
    let
        selectedIndex =
            indexOfValue (Environment.getFormValue identifier env) values

        isSelected index =
            Maybe.map ((==) index) selectedIndex
                |> Maybe.withDefault False
    in
        div []
            (List.indexedMap
                (\index value -> renderOption value (isSelected index) onChange)
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


indexOfValue : Maybe Value -> List String -> Maybe Int
indexOfValue existingValue values =
    Maybe.andThen Values.asString existingValue
        |> Maybe.andThen (\val -> List.findIndex ((==) val) values)
