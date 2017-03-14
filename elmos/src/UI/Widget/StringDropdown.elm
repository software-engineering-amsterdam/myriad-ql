module UI.Widget.StringDropdown exposing (view)

import Html exposing (Html, select)
import Html.Attributes exposing (class, disabled, id)
import List.Extra as List
import UI.Widget.Base exposing (WidgetContext)
import UI.Widget.DropdownBase exposing (selectEventHandler, renderPlaceholder, renderOption)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


view : WidgetContext msg -> List String -> Html msg
view { identifier, env, onChange, editable } values =
    let
        selectedValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asString
    in
        -- index of labels starts with 0, however 0 = the default option, 1 = first item, 2 = second item
        select
            [ class "form-control"
            , selectEventHandler (onChange << indexToValue values)
            , disabled (not editable)
            , id identifier
            ]
            (renderPlaceholder
                :: List.map
                    (\value -> renderOption (Just value == selectedValue) value)
                    values
            )


indexToValue : List String -> Maybe Int -> Value
indexToValue values selectedIndex =
    selectedIndex
        |> Maybe.andThen (\index -> List.getAt (index - 1) values)
        |> Maybe.map Values.string
        |> Maybe.withDefault Values.undefined
