module QLS.Parser.Style exposing (style)

import Combine exposing (Parser, choice, string, regex, (<$>), (*>))
import Combine.Num exposing (int)
import QLS.AST exposing (Style(Width, Font, FontSize, Color))
import QL.Parser.Token exposing (quotedString)
import Combine.Extra exposing (whitespace1)


style : Parser state Style
style =
    choice
        [ property "width" (Width <$> int)
        , property "font" (Font <$> quotedString)
        , property "fontsize" (FontSize <$> int)
        , property "color" (Color <$> colorValueParser)
        ]


property : String -> Parser state Style -> Parser state Style
property name valueParser =
    string name *> string ":" *> whitespace1 *> valueParser


colorValueParser : Parser s String
colorValueParser =
    choice
        [ regex "#(\\d{6}|\\d{3})"
        , choice (List.map string defaultColors)
        ]


defaultColors : List String
defaultColors =
    [ "green", "red", "blue" ]
