module UI.Widget.BooleanDropdown exposing (view)

import Html exposing (Html, div, select, option, text)
import Html.Attributes exposing (type_, id, class, selected, size, disabled)
import Json.Decode as Json
import Html.Events exposing (on, onClick)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import Html.Events.Extra exposing (targetSelectedIndex)
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange } labels =
    let
        selectedIndex =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asBool
                |> indexFromValue

        hasNoSelectedValued : Bool
        hasNoSelectedValued =
            (selectedIndex == 0)
    in
        select [ class "form-control", selectEventHandler (onChange << indexToBoolValue) ]
            (renderDefaultOption hasNoSelectedValued
                :: (List.indexedMap
                        -- index of labels starts with 0, however 0 = the default option, 1 = true = 2 = false
                        (\index label -> renderOption index label ((index + 1) == selectedIndex) onChange)
                        labels
                   )
            )


renderDefaultOption : Bool -> Html msg
renderDefaultOption isSelected =
    option
        [ selected isSelected, disabled True ]
        [ text "Select value" ]


renderOption : Int -> String -> Bool -> (Value -> msg) -> Html msg
renderOption index optionLabel isSelected onChange =
    option
        [ selected isSelected ]
        [ text optionLabel ]


selectEventHandler : (Maybe Int -> msg) -> Html.Attribute msg
selectEventHandler onSelect =
    on "change"
        (Json.map onSelect targetSelectedIndex)


indexToBoolValue : Maybe Int -> Value
indexToBoolValue maybeIndex =
    case maybeIndex of
        Just index ->
            Values.bool (index == 1)

        Nothing ->
            Values.undefined


indexFromValue : Maybe Bool -> Int
indexFromValue value =
    case value of
        Just true ->
            if true then
                1
            else
                2

        Nothing ->
            0
