module QLS.Parser.Widget exposing (widget)

import Combine exposing ((*>), (<$), (<$>), Parser, choice, parens, sepBy1, string)
import Combine.Extra exposing (trimmed)
import Combine.Num exposing (int)
import QL.Parser.Token exposing (quotedString)
import QLS.AST exposing (..)


widget : Parser state Widget
widget =
    choice [ radio, spinbox, checkbox, text, slider ]


radio : Parser state Widget
radio =
    Radio
        <$> (string "radio" *> widgetParams radioOption)


radioOption : Parser state String
radioOption =
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
        <$> (string "slider" *> widgetParams int)


text : Parser state Widget
text =
    Text <$ string "text"


checkbox : Parser state Widget
checkbox =
    Checkbox <$ string "checkbox"
