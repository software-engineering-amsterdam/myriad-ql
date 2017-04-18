module UI.Widget.DropdownBase exposing (selectEventHandler, renderPlaceholder, renderOption)

import Json.Decode as Json
import Html.Events.Extra exposing (targetSelectedIndex)
import Html.Events exposing (on)
import Html exposing (Html, option, text)
import Html.Attributes exposing (selected, disabled)


selectEventHandler : (Maybe Int -> msg) -> Html.Attribute msg
selectEventHandler onSelect =
    on "change" (Json.map onSelect targetSelectedIndex)


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
