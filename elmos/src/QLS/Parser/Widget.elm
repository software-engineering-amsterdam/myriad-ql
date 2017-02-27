module QLS.Parser.Widget exposing (widget)

import Combine exposing (Parser, choice, parens, sepBy1, string, (<$>), (<$), (*>))
import QLS.AST exposing (Widget(Checkbox, Spinbox, Radio))
import QL.Parser.Token exposing (quotedString)
import Combine.Extra exposing (trimmed)


widget : Parser state Widget
widget =
    choice [ radio, spinbox, checkbox ]


radio : Parser state Widget
radio =
    Radio
        <$> (string "radio"
                *> parens
                    (trimmed
                        (sepBy1 (trimmed (string ","))
                            radioOption
                        )
                    )
            )


radioOption : Parser state String
radioOption =
    quotedString


spinbox : Parser state Widget
spinbox =
    Spinbox <$ string "spinbox"


checkbox : Parser state Widget
checkbox =
    Checkbox <$ string "checkbox"
