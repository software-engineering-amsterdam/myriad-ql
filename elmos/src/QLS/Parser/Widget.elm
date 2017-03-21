module QLS.Parser.Widget exposing (widget)

import Combine exposing ((*>), (<$), (<$>), (>>=), Parser, choice, parens, sepBy1, string, fail, succeed)
import Combine.Extra exposing (trimmed)
import Combine.Num exposing (int)
import QL.Parser.Token exposing (quotedString)
import QLS.AST exposing (..)


widget : Parser state Widget
widget =
    choice [ radio, spinbox, checkbox, text, slider, dropdown ]


radio : Parser state Widget
radio =
    Radio
        <$> (string "radio" *> widgetParams option)


dropdown : Parser state Widget
dropdown =
    Dropdown
        <$> (string "dropdown" *> widgetParams option)


option : Parser state String
option =
    quotedString


widgetParams : Parser s a -> Parser s (List a)
widgetParams paramParser =
    parens (trimmed (sepBy1 (trimmed (string ",")) paramParser))


spinbox : Parser state Widget
spinbox =
    Spinbox <$ string "spinbox"


slider : Parser state Widget
slider =
    Slider
        <$> (string "slider" *> (widgetParams int >>= asSliderArgs))


asSliderArgs : List Int -> Parser state SliderArgs
asSliderArgs args =
    case args of
        [ x ] ->
            succeed (SliderMax x)

        [ x, y ] ->
            succeed (SliderMinMax x y)

        _ ->
            fail ("Incorrect arguments for slider. Expected one or two arguments, but got " ++ toString (List.length args))


text : Parser state Widget
text =
    Text <$ string "text"


checkbox : Parser state Widget
checkbox =
    Checkbox <$ string "checkbox"
