module UI.Widget.BooleanDropdown exposing (view)

import Html exposing (Html, select)
import Html.Attributes exposing (class, disabled, id)
import UI.Widget.Base exposing (WidgetContext)
import UI.Widget.DropdownBase exposing (selectEventHandler, renderPlaceholder, renderOption)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange, editable } labels =
    let
        selectedIndex =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asBool
                |> indexFromValue
    in
        -- index of labels starts with 0, however 0 = the default option, 1 = true, 2 = false
        select
            [ class "form-control"
            , id identifier
            , selectEventHandler (onChange << indexToBoolValue)
            , disabled (not editable)
            ]
            (renderPlaceholder
                :: List.indexedMap
                    (\index optionLabel -> renderOption ((index + 1) == selectedIndex) optionLabel)
                    labels
            )


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
