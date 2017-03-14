module UI.Widget.BooleanDropdown exposing (view)

import Html exposing (Html, select, option, text)
import Html.Attributes exposing (class, selected, disabled)
import Json.Decode as Json
import Html.Events exposing (on)
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
    in
        -- index of labels starts with 0, however 0 = the default option, 1 = true = 2 = false
        select [ class "form-control", selectEventHandler (onChange << indexToBoolValue) ]
            (renderPlaceholder
                :: List.indexedMap
                    (\index optionLabel -> renderOption ((index + 1) == selectedIndex) optionLabel)
                    labels
            )


renderPlaceholder : Html msg
renderPlaceholder =
    option
        [ selected True, disabled True ]
        [ text "Select option" ]


renderOption : Bool -> String -> Html msg
renderOption isSelected optionLabel =
    option
        [ selected isSelected ]
        [ text optionLabel ]


selectEventHandler : (Maybe Int -> msg) -> Html.Attribute msg
selectEventHandler onSelect =
    on "change" (Json.map onSelect targetSelectedIndex)


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
        Nothing ->
            0

        Just true ->
            if true then
                1
            else
                2
