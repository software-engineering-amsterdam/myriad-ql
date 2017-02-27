module QLS.Parser.Style exposing (style)

import Combine exposing (Parser, choice, string, regex, (<$>), (*>))
import Combine.Num exposing (int)
import QLS.AST exposing (Style(Width, Font, FontSize, Color))
import QL.Parser.Token exposing (quotedString)
import Combine.Extra exposing (whitespace1)


style : Parser state Style
style =
    choice
        [ stylePair Width "width" int
        , stylePair Font "font" quotedString
        , stylePair FontSize "fontsize" int
        , stylePair Color "color" colorValueParser
        ]


stylePair : (value -> Style) -> String -> Parser state value -> Parser state Style
stylePair f name valueParser =
    string name *> string ":" *> whitespace1 *> (f <$> valueParser)


colorValueParser : Parser s String
colorValueParser =
    choice
        [ regex "#(\\d{6}|\\d{3})"
        , choice (List.map string defaultColors)
        ]


defaultColors : List String
defaultColors =
    [ "green", "red", "blue" ]
