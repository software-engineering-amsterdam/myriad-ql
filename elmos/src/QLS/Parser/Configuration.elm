module QLS.Parser.Configuration exposing (configuration)

import Combine exposing (Parser, maybe, or, sepBy, string, whitespace, (<$>), (*>), (<*), (<*>))
import QLS.AST exposing (Configuration(SingleConfig, MultiConfig), Widget)
import QLS.Parser.Style exposing (style)
import QLS.Parser.Widget exposing (widget)
import Combine.Extra exposing (whitespace1, trimmed)


configuration : Parser state Configuration
configuration =
    or
        (SingleConfig <$> widgetConfig)
        (MultiConfig
            <$> (string "{" *> trimmed (sepBy whitespace1 style))
            <*> (whitespace *> maybe widgetConfig <* whitespace <* string "}")
        )


widgetConfig : Parser state Widget
widgetConfig =
    string "widget" *> whitespace1 *> widget
